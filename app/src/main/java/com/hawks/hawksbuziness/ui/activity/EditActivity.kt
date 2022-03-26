package com.hawks.hawksbuziness.ui.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.MaterialDatePicker
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.ActivityEditBinding
import com.hawks.hawksbuziness.model.profile.Auth
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.profile.ProfileViewModel
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.URIPathHelper
import com.hawks.hawksbuziness.utill.fixRotation
import com.hawks.hawksbuziness.utill.readWritePermission
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class EditActivity : AppCompatActivity() {
    private val profileViewModel: ProfileViewModel by viewModels<ProfileViewModel>()
    private val REQUESTCODE = 100
    private var enode_Image: String? = null
    private lateinit var progressDialog: ProgressDialog

    @Inject
    lateinit var preferenceManger: PreferenceManger
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)
        binding.viewmodel = profileViewModel
        binding.lifecycleOwner = this
        binding.click = ClickHandler()


        initializeData()
        observeData()
    }

    private fun observeData(){
        profileViewModel.response.observe(this, Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                    progressDialog.show()
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Succes -> {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()
                    if (it.result.status != 0) {
                        saveToLocal(it.result.auth)
                    }
                    finish()
                }
            }
        })
    }

    private fun saveToLocal(auth: Auth) {
        profileViewModel.updateProfile(auth)
    }


    private fun initializeData() {
        progressDialog= ProgressDialog(this)
        profileViewModel.getProfileData().observe(this, Observer {
            saveData(it)
        })
    }

    private fun saveData(result: Auth) {
        profileViewModel.dob.value = result.dob ?: ""
        profileViewModel.email.value = result.email ?: ""
        profileViewModel.gender.value = result.gender ?: ""
        profileViewModel.image.value = result.image ?: ""
        profileViewModel.mobile.value = result.mobile ?: ""
        profileViewModel.name.value = result.username ?: ""
        profileViewModel.nationality.value = result.nationality ?: ""
        profileViewModel.place.value = result.place ?: ""
        profileViewModel.country.value = result.country ?: ""


    }

    private fun openGallery() {

        val photoPickerIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        photoPickerIntent.type = "image/*"
        val mimeType = arrayOf("image/jpeg", "image/png", "image/jpg")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        getResult.launch(Intent.createChooser(photoPickerIntent, "select Image"))


    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val selectedImageuri: Uri? = data?.data
                if (null != selectedImageuri) {
                    enode_Image = encodeImage(selectedImageuri)

                }
            }


        }

    private fun encodeImage(uri: Uri): String {

//
        val inputStream = contentResolver.openInputStream(uri)
        val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
        val uriPathHelper = URIPathHelper()
        val filePath = uriPathHelper.getPath(this,uri)

        val rotated=bitmap.fixRotation(filePath!!)

        binding.profileIcon.setImageBitmap(rotated)


        val byteArrayOutputStream = ByteArrayOutputStream()
        rotated?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byte = byteArrayOutputStream.toByteArray()

        return Base64.encodeToString(byte, Base64.DEFAULT)
    }




    inner class ClickHandler {
        fun imageChooser(view: View) {
            if (readWritePermission()){
                openGallery()
            }

        }

        fun selectDate(view: View) {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
            datePicker.show(supportFragmentManager, "select date")
            datePicker.addOnPositiveButtonClickListener {
                val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                utc.timeInMillis = it
                val format = SimpleDateFormat("yyyy-MM-dd")
                val formatted: String = format.format(utc.time)
                profileViewModel.dob.value = formatted

            }
        }

        fun updateProfile(view: View) {
            val hashMap=HashMap<String,String>()
            hashMap["user_id"]=preferenceManger.getUserId()
            hashMap["dob"]=profileViewModel.dob.value.toString()
            hashMap["gender"]=profileViewModel.gender.value.toString()
            hashMap["mobile"]=profileViewModel.mobile.value.toString()
            hashMap["email"]=profileViewModel.email.value.toString()
            hashMap["nationality"]=profileViewModel.nationality.value.toString()
            hashMap["country"]=profileViewModel.country.value.toString()
            hashMap["username"]=profileViewModel.name.value.toString()
            enode_Image?.let {
                hashMap["image"]=it
            }

            hashMap["place"]=profileViewModel.place.value.toString()

            profileViewModel.updateProfileData(hashMap)



        }
    }

    fun modifyOrientation(bitmap: Bitmap, image_absolute_path: String?): Bitmap? {
        val ei = ExifInterface(image_absolute_path!!)
        val orientation =
            ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotate(bitmap, 90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotate(bitmap, 180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotate(bitmap, 270f)
//            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flip(bitmap, true, false)
//            ExifInterface.ORIENTATION_FLIP_VERTICAL -> flip(bitmap, false, true)
            else -> bitmap
        }
    }

    fun rotate(bitmap: Bitmap, degrees: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(degrees)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

//    fun flip(bitmap: Bitmap, horizontal: Boolean, vertical: Boolean): Bitmap? {
//        val matrix = Matrix()
//        matrix.preScale(if (horizontal) -1 else 1, if (vertical) -1 else 1)
//        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
//    }


}
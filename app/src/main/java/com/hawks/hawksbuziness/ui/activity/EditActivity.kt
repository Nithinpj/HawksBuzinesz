package com.hawks.hawksbuziness.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.navOptions
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.ActivityEditBinding
import com.hawks.hawksbuziness.model.profile.Auth
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.profile.ProfileViewModel
import com.hawks.hawksbuziness.utill.ResponceState
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

@AndroidEntryPoint
class EditActivity : AppCompatActivity() {
    private val profileViewModel: ProfileViewModel by viewModels<ProfileViewModel>()
    private val REQUESTCODE = 100
    private var enode_Image: String? = null

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
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Succes -> {
                    Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()
                    if (it.result.status != 0) {
                        saveToLocal(it.result.auth)
                    }


                }
            }
        })
    }

    private fun saveToLocal(auth: Auth) {
        profileViewModel.updateProfile(auth)
    }


    private fun initializeData() {
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
        profileViewModel.name.value = result.name ?: ""
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
        val inputStream = contentResolver.openInputStream(uri)
        val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
        binding.profileIcon.setImageBitmap(bitmap)

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byte = byteArrayOutputStream.toByteArray()

        return Base64.encodeToString(byte, Base64.DEFAULT)
    }




    inner class ClickHandler {
        fun imageChooser(view: View) {
            openGallery()
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
            hashMap["image"]=enode_Image.toString()
            hashMap["place"]=profileViewModel.place.value.toString()

            profileViewModel.updateProfileData(hashMap)



        }
    }


}
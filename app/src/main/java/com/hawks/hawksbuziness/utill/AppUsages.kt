package com.hawks.hawksbuziness.utill

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.model.contacts.Contacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.Contract
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.log


fun Fragment.showSnackBar(view: View) {
    Snackbar.make(view, "Ticket Raised", Snackbar.LENGTH_SHORT)
        .show()
}

@BindingAdapter("logoImage")
fun setImage(view: ImageView, url: String?) {
    if (url != null) {
        view.load(
            "${Constants.imageUrl}$url"
        ) {
            placeholder(R.drawable.logo_)
        }
    } else {
        view.setImageResource(R.drawable.logo_)
    }

}

@BindingAdapter("profileImage")
fun seProfiletImage(view: ImageView, url: String?) {
    if (url != null) {
        view.load(
            "${Constants.profileimageUrl}$url"
        ) {
            placeholder(R.drawable.ic_baseline_account_circle_24)
            error(R.drawable.ic_baseline_account_circle_24)
        }
    } else {
        view.setImageResource(R.drawable.ic_baseline_account_circle_24)
    }
}

fun Activity.isLocationPermissionGranted(): Boolean {
    return if (ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_CONTACTS
            ),
            100
        )
        false
    } else {
        true
    }


}

fun Activity.readWritePermission():Boolean{
    return if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            100
        )
        false
    }else{
        true
    }
}

fun Fragment.showToast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Activity.hideKeyboard() {
    val inputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}

fun Fragment.hideKeyboards() {
    val inputMethodManager =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
}

fun Fragment.whatsApp(CONTACT:String) {
    try {
        val url = "https://api.whatsapp.com/send?phone=+91 $CONTACT"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    } catch (e: ActivityNotFoundException) {
    }

}

fun Fragment.Dial(CONTACT:String) {
    val intent = Intent()
    intent.action = Intent.ACTION_DIAL
    intent.data = Uri.parse("tel:$CONTACT")
    startActivity(intent)

}

fun Fragment.email(email:String) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this

        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, "")
        startActivity(intent)
    }catch (ex:ActivityNotFoundException){}

}

fun Bitmap.fixRotation(path: String): Bitmap? {

    val ei = ExifInterface(path)

    val orientation: Int = ei.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )

    return when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(270f)
        ExifInterface.ORIENTATION_NORMAL -> this
        else -> this
    }
}


fun Bitmap.rotateImage(angle: Float): Bitmap? {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(
        this, 0, 0, width, height,
        matrix, true
    )
}



fun Activity.compressImage(imageUri: String): String? {
    val filePath = imageUri
    var scaledBitmap: Bitmap? = null
    val options = BitmapFactory.Options()

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
    options.inJustDecodeBounds = true
    var bmp = BitmapFactory.decodeFile(filePath, options)
    var actualHeight = options.outHeight
    var actualWidth = options.outWidth

//      max Height and width values of the compressed image is taken as 816x612
    val maxHeight = 816.0f
    val maxWidth = 612.0f
    var imgRatio = (actualWidth / actualHeight).toFloat()
    val maxRatio = maxWidth / maxHeight

//      width and height values are set maintaining the aspect ratio of the image
    if (actualHeight > maxHeight || actualWidth > maxWidth) {
        if (imgRatio < maxRatio) {
            imgRatio = maxHeight / actualHeight
            actualWidth = (imgRatio * actualWidth).toInt()
            actualHeight = maxHeight.toInt()
        } else if (imgRatio > maxRatio) {
            imgRatio = maxWidth / actualWidth
            actualHeight = (imgRatio * actualHeight).toInt()
            actualWidth = maxWidth.toInt()
        } else {
            actualHeight = maxHeight.toInt()
            actualWidth = maxWidth.toInt()
        }
    }

//      setting inSampleSize value allows to load a scaled down version of the original image
    options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)

//      inJustDecodeBounds set to false to load the actual bitmap
    options.inJustDecodeBounds = false

//      this options allow android to claim the bitmap memory if it runs low on memory
    options.inPurgeable = true
    options.inInputShareable = true
    options.inTempStorage = ByteArray(16 * 1024)
    try {
//          load the bitmap from its path
        bmp = BitmapFactory.decodeFile(filePath, options)
    } catch (exception: OutOfMemoryError) {
        exception.printStackTrace()
    }
    try {
        scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
    } catch (exception: OutOfMemoryError) {
        exception.printStackTrace()
    }
    val ratioX = actualWidth / options.outWidth.toFloat()
    val ratioY = actualHeight / options.outHeight.toFloat()
    val middleX = actualWidth / 2.0f
    val middleY = actualHeight / 2.0f
    val scaleMatrix = Matrix()
    scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
    val canvas = Canvas(scaledBitmap!!)
    canvas.setMatrix(scaleMatrix)
    canvas.drawBitmap(
        bmp,
        middleX - bmp.width / 2,
        middleY - bmp.height / 2,
        Paint(Paint.FILTER_BITMAP_FLAG)
    )

//      check the rotation of the image and display it properly
    val exif: ExifInterface
    try {
        exif = ExifInterface(filePath!!)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION, 0
        )
        Log.d("EXIF", "Exif: $orientation")
        val matrix = Matrix()
        if (orientation == 6) {
            matrix.postRotate(90f)
            Log.d("EXIF", "Exif: $orientation")
        } else if (orientation == 3) {
            matrix.postRotate(180f)
            Log.d("EXIF", "Exif: $orientation")
        } else if (orientation == 8) {
            matrix.postRotate(270f)
            Log.d("EXIF", "Exif: $orientation")
        }
        scaledBitmap = Bitmap.createBitmap(
            scaledBitmap!!, 0, 0,
            scaledBitmap.width, scaledBitmap.height, matrix,
            true
        )
    } catch (e: IOException) {
        e.printStackTrace()
    }
    var out: FileOutputStream? = null
    val filename = getFilename()
    try {
        out = FileOutputStream(filename)

//          write the compressed bitmap at the destination specified by filename.
        scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, 80, out)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }
    return filename
}

fun getFilename(): String {
    val file = File(Environment.getExternalStorageDirectory().path, "MyFolder/Images")
    if (!file.exists()) {
        file.mkdirs()
    }
    return file.getAbsolutePath().toString() + "/" + System.currentTimeMillis() + ".jpg"
}

private fun getRealPathFromURI(contentURI: String, activity: Activity): String? {
    val contentUri = Uri.parse(contentURI)
    val cursor: Cursor? = activity.applicationContext.contentResolver.query(contentUri, null, null, null, null)
    return if (cursor == null) {
        contentUri.path
    } else {
        cursor.moveToFirst()
        val index: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        cursor.getString(index)
    }
}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1
    if (height > reqHeight || width > reqWidth) {
        val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
        val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
        inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
    }
    val totalPixels = (width * height).toFloat()
    val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()
    while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
        inSampleSize++
    }
    return inSampleSize
}

@SuppressLint("Range")
suspend fun Activity.getContactList():List<Contacts> {

    val numList= arrayListOf<Contacts>()
    withContext(Dispatchers.IO){

        val cr: ContentResolver = contentResolver
        val cur: Cursor? = cr.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )
        if ((cur?.count ?: 0) > 0) {

            while (cur != null && cur.moveToNext()) {
                var contact= Contacts()
                val id: String = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts._ID)
                )
                val name: String? = cur.getString(
                    cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                    )
                )
                contact.name=name
                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val pCur: Cursor? = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        null
                    )
                    pCur?.let {
                        while (pCur.moveToNext()) {
                            val phoneNo: String = pCur.getString(
                                pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER
                                )
                            )
                            contact.number=phoneNo
//                        numList.add(phoneNo)
//                        Log.e("TAG", "getContactList: $name")
//                        Log.e("TAG", "getContactList: $phoneNo")
                        }
                        pCur.close()
                    }

                }
                if (contact.name.isNullOrEmpty()||contact.number.isNullOrEmpty()){

                }else{
                    numList.add(contact)
                }

            }
        }
        if (cur != null) {
            cur.close()
        }

    }
    return numList

}

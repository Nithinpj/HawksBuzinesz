package com.hawks.hawksbuziness.utill

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.hawks.hawksbuziness.R


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
fun seProfiletImage(view: ImageView,url: String?)
{
    if (url != null) {
        view.load(
            "${Constants.profileimageUrl}$url"
        ) {
            placeholder(R.drawable.ic_baseline_account_circle_24)
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
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            100
        )
        false
    } else {
        true
    }
}

fun Fragment.showToast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.hideKeyboard() {
    val inputMethodManager =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
}

fun Fragment.whatsApp() {
    try {
        val url = "https://api.whatsapp.com/send?phone=$CONTACT"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }catch (e:ActivityNotFoundException){}

}

fun Fragment.Dial(){
    val intent = Intent()
    intent.action = Intent.ACTION_DIAL
    intent.data = Uri.parse("tel:$CONTACT")
    startActivity(intent)

}

fun Fragment.email(){
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:") // only email apps should handle this

    intent.putExtra(Intent.EXTRA_EMAIL, "nighil05@gmail.com")
    intent.putExtra(Intent.EXTRA_SUBJECT, "")
    startActivity(intent)
}
const val CONTACT="+91 97787 70993";


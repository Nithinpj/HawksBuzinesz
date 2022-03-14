package com.hawks.hawksbuziness.utill

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
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


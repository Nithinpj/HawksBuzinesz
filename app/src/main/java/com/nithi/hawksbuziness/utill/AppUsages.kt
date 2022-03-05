package com.nithi.hawksbuziness.utill

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(view:View){
    Snackbar.make(view, "Ticket Raised", Snackbar.LENGTH_SHORT)
        .show()
}

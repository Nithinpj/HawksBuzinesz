package com.hawks.hawksbuziness

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.getContactList
import kotlinx.android.synthetic.main.fragment_test.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.settings.javaScriptEnabled=true
        webView.loadUrl("https://businezapp.com/")

    }





}
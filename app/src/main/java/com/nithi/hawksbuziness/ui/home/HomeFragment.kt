package com.nithi.hawksbuziness.ui.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nithi.hawksbuziness.R
import com.nithi.hawksbuziness.preferences.PreferenceManger
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var preferenceManger: PreferenceManger
    private val viemodel:HomeViemodel by viewModels<HomeViemodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (preferenceManger.getUrl().isNotEmpty()){

            setData(preferenceManger.getUrl())
        }else viemodel.getWeb()

        viemodel.response.observe(requireActivity(), Observer {
            when(it){
                is ResponceState.Failiure -> {

                }
                is ResponceState.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Succes -> {
                    preferenceManger.saveUrl(it.result.data)
                    setData(it.result.data)
                }
            }
        })
    }



    @SuppressLint("SetJavaScriptEnabled")
    private fun setData(data: String) {

        webView.settings.javaScriptEnabled=true
        webView.webViewClient=object :WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress_circular?.let {
                    it.visibility=View.VISIBLE
                }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress_circular?.let {
                    it.visibility=View.GONE
                }
            }
        }
        webView.loadUrl(data)
    }


}

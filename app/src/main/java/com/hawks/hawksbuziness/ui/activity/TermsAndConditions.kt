package com.hawks.hawksbuziness.ui.activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.ActivityTermsAndConditionsBinding
import com.hawks.hawksbuziness.model.info.Data
import com.hawks.hawksbuziness.ui.home.HomeViemodel
import com.hawks.hawksbuziness.utill.ResponceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class TermsAndConditions : AppCompatActivity() {
    private val viewModel by viewModels<HomeViemodel>()
    lateinit var binding:ActivityTermsAndConditionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_terms_and_conditions)

        viewModel.getInfo().observe(this, Observer {
            when(it){
                is ResponceState.Failiure -> {
                    binding.progressCircular.visibility=View.GONE
                }
                is ResponceState.Loading -> {
                    binding.progressCircular.visibility=View.VISIBLE
                }
                is ResponceState.Succes -> {
                    binding.progressCircular.visibility=View.GONE
                    saveData(it.result.data)
                }
            }
        })
    }

    private fun saveData(data: Data) {
        setView(data.terms.toString())
    }
    private fun setView(data: String) {
        binding.webView.clearCache(true)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressCircular?.let { progress ->
                    progress.visibility = View.VISIBLE
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
                binding.progressCircular?.let { progress ->
                    progress.visibility = View.GONE
                }
                binding.swipeRefresh.isRefreshing = false

            }
        }
        binding.webView.loadUrl(data)

        binding.swipeRefresh.setOnRefreshListener {
            binding.webView.reload()
        }


    }
}
package com.hawks.hawksbuziness

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
import androidx.navigation.fragment.findNavController
import com.hawks.hawksbuziness.databinding.FragmentTermsPrivacyBinding
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class TermsPrivacyFragment : Fragment() {

    @Inject
    lateinit var preferenceManger:PreferenceManger
    lateinit var binding:FragmentTermsPrivacyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.fragmentName="TERMS"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentTermsPrivacyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key=arguments?.getString("value")
        if (key.equals("terms")){
            if (!preferenceManger.getTerms().isNullOrEmpty()){
                setView(preferenceManger.getTerms())
            }
        }else{
           if (!preferenceManger.getPrivacy().isNullOrEmpty()){
               setView(preferenceManger.getPrivacy())
           }
        }

        binding.appbar.back.setOnClickListener {
            findNavController().navigateUp()
        }

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
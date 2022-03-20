package com.hawks.hawksbuziness.ui.home

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
import com.hawks.hawksbuziness.databinding.FragmentHomeBinding
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.utill.ResponceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var preferenceManger: PreferenceManger
    private val viemodel:HomeViemodel by viewModels<HomeViemodel>()
    private lateinit var binding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        MainActivity.fragmentName="HOME"
        binding= FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (preferenceManger.getUrl().isNotEmpty()){

            setData(preferenceManger.getUrl())
        }else{
            viemodel.getWeb()
        }


        viemodel.response.observe(requireActivity(), Observer { it ->
            when(it){
                is ResponceState.Failiure -> {
                    progress_circular?.let { progress->
                        progress.visibility=View.GONE
                    }
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                    progress_circular?.let { progress->
                        progress.visibility=View.VISIBLE
                    }

                }
                is ResponceState.Succes -> {
                    progress_circular?.let { progress->
                        progress.visibility=View.GONE
                    }
                    preferenceManger.saveUrl(it.result.data)
                    setData(it.result.data)
                }
            }
        })
    }



    @SuppressLint("SetJavaScriptEnabled")
    private fun setData(data: String) {
        binding.webView.clearCache(true)
        binding.webView.settings.javaScriptEnabled=true
        binding.webView.webViewClient=object :WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress_circular?.let { progress->
                    progress.visibility=View.VISIBLE
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
                progress_circular?.let { progress->
                    progress.visibility=View.GONE
                }
                binding.swipeRefresh.isRefreshing=false

            }
        }
        binding.webView.loadUrl(data)

        binding.swipeRefresh.setOnRefreshListener {
            binding.webView.reload()
        }
    }

    override fun onResume() {
        super.onResume()
    }



}

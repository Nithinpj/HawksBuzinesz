package com.hawks.hawksbuziness.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.FragmentHomeBinding
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.onBackPressedListener
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(),onBackPressedListener {

    @Inject
    lateinit var preferenceManger: PreferenceManger
    private val viemodel: HomeViemodel by viewModels<HomeViemodel>()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        MainActivity.fragmentName = "HOME"
        binding = FragmentHomeBinding.inflate(inflater)
        (activity as MainActivity?)?.setOnBackPressedListenter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (preferenceManger.getUrl().isNotEmpty()) {

            setData(preferenceManger.getUrl())
        } else {
            viemodel.getWeb()
        }


        viemodel.response.observe(requireActivity(), Observer { it ->
            when (it) {
                is ResponceState.Failiure -> {
                    progress_circular?.let { progress ->
                        progress.visibility = View.GONE
                    }
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                    progress_circular?.let { progress ->
                        progress.visibility = View.VISIBLE
                    }

                }
                is ResponceState.Succes -> {
                    progress_circular?.let { progress ->
                        progress.visibility = View.GONE
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

        if (Build.VERSION.SDK_INT >= 21) {
            binding.webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }

        //FOR WEBPAGE SLOW UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            binding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            binding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.addJavascriptInterface(JavaScriptInterface(requireContext()), "Android")
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress_circular?.let { progress ->
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
                progress_circular?.let { progress ->
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


    override fun onResume() {
        super.onResume()

    }

    inner class JavaScriptInterface(c: Context) {
        var mContext: Context

        /** Instantiate the interface and set the context  */
        init {
            mContext = c
        }


        @JavascriptInterface
        fun showToast(data: String?) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, data)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }


    }

    override fun onBackPressed() {
        if (binding.webView != null) {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                MaterialAlertDialogBuilder(requireContext())

                    .setMessage(resources.getString(R.string.exit))

                    .setNegativeButton("No") { dialog, which ->
                        // Respond to negative button press
                        dialog.dismiss()
                    }
                    .setPositiveButton("yes") { dialog, which ->
                        // Respond to positive button press
                        activity?.finish()
                    }
                    .show()

            }
        }
    }



}

package com.hawks.hawksbuziness.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hawks.hawksbuziness.databinding.FragmentLanguageBinding
import com.hawks.hawksbuziness.model.languages.Data
import com.hawks.hawksbuziness.ui.activity.ReferenceViewModel
import com.hawks.hawksbuziness.ui.home.HomeViemodel
import com.hawks.hawksbuziness.ui.settings.adapter.LanguageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageDialogFragment(val click:(Data)->Unit):DialogFragment() {
    private lateinit var binding: FragmentLanguageBinding
    private val viewModel: HomeViemodel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLanguageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllLanguages().observe(requireActivity(), Observer {
            setData(it)
        })


    }

    private fun setData(it: List<Data>?) {

        val adapter = LanguageAdapter(it as ArrayList<Data>) { click -> SelectLanguage(click) }
        binding.languageList.adapter=adapter
    }

    private fun SelectLanguage(data: Data) {
        click(data)
        dialog?.dismiss()
    }

    override fun onStart() {
        super.onStart()
        super.onResume()
        val window = dialog!!.window ?: return
        val params = window.attributes
//        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.width =700
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = params

//        dialog?.window?.setLayout(
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.WRAP_CONTENT
//        )
    }

}



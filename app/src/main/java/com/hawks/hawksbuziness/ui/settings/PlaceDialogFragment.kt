package com.hawks.hawksbuziness.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.LayoutPlacesBinding
import com.hawks.hawksbuziness.model.places.Data
import com.hawks.hawksbuziness.model.shops.Shop
import com.hawks.hawksbuziness.ui.settings.adapter.PlaceAdapter
import com.hawks.hawksbuziness.ui.shops.ShopViewmodel
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceDialogFragment(val name:String,val click:(Data)->Unit) : DialogFragment() {
    private val viemodel: ShopViewmodel by viewModels<ShopViewmodel>()
    lateinit var binding: LayoutPlacesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= LayoutPlacesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viemodel.getAllPlces().observe(requireActivity(), Observer {
            setPlaceData(it)
        })


    }

    private fun setPlaceData(it: List<Data>?) {
        val adapter=PlaceAdapter(name,it as ArrayList<Data>) { click -> setplaceClick(click) }
        binding.placeList.adapter=adapter

    }

    private fun setplaceClick(click: Data) {
        dialog?.dismiss()
        click(click)

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
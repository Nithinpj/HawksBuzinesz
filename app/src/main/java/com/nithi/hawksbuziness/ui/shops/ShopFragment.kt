package com.nithi.hawksbuziness.ui.shops

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nithi.hawksbuziness.R
import com.nithi.hawksbuziness.databinding.ShopFragmentBinding
import com.nithi.hawksbuziness.model.shops.Shop
import com.nithi.hawksbuziness.ui.shops.adapter.AllShopAdapter
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var bindiShopFragmentBinding: ShopFragmentBinding
    val viemmodel: ShopViewmodel by viewModels<ShopViewmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindiShopFragmentBinding = ShopFragmentBinding.inflate(inflater)
        return bindiShopFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viemmodel.getShops()
        observeData()
    }

    private fun observeData() {
        viemmodel.shopMutableLiveData.observe(requireActivity(), Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    bindiShopFragmentBinding.progressCircular.visibility=View.GONE
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                    bindiShopFragmentBinding.progressCircular.visibility=View.VISIBLE
                }
                is ResponceState.Succes -> {
                    bindiShopFragmentBinding.progressCircular.visibility=View.GONE
                    viewShops(it.result.shops)
                }
            }
        })
    }

    private fun viewShops(shops: List<Shop>) {
        val adapter=AllShopAdapter{data->onClicked(data)}
        adapter.bind(shops)
       bindiShopFragmentBinding.adapter=adapter
        adapter.notifyDataSetChanged()
    }

    private fun onClicked(data: Shop) {
        val bundle=Bundle()
        bundle.putSerializable("data",data)
        findNavController().navigate(R.id.action_shopfragment_to_addUpdateFragment)
    }

}
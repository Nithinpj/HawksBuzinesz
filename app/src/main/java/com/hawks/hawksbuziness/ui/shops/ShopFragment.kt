package com.hawks.hawksbuziness.ui.shops

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.ShopFragmentBinding
import com.hawks.hawksbuziness.model.shops.Shop
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.ui.shops.adapter.AllShopAdapter
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var bindiShopFragmentBinding: ShopFragmentBinding
    val viemmodel: ShopViewmodel by viewModels<ShopViewmodel>()
    var user_id: Int? = null

    @Inject
    lateinit var preferenceManger: PreferenceManger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        MainActivity.fragmentName = "SHOP"
        bindiShopFragmentBinding = ShopFragmentBinding.inflate(inflater)
        return bindiShopFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!preferenceManger.getUserId().isNullOrEmpty()) {
            viemmodel.getShops()
        } else {

            showBottomDialog()
        }

        observeData()
        bindiShopFragmentBinding.appbar.back.setOnClickListener {
            findNavController().popBackStack(R.id.shopfragment, true);
            findNavController().navigate(R.id.homeFragment);
        }

        bindiShopFragmentBinding.appbar.addMore.visibility = View.VISIBLE
        bindiShopFragmentBinding.appbar.addMore.setOnClickListener {

            if (!preferenceManger.getUserId().isNullOrEmpty()) {
                val bundle = Bundle()
                bundle.putString("type", "add")
                findNavController().navigate(R.id.action_shopfragment_to_addUpdateFragment, bundle)
            } else {
                showBottomDialog()
            }

        }
    }

    private fun observeData() {
        viemmodel.shopMutableLiveData.observe(requireActivity(), Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                    bindiShopFragmentBinding.progressCircular.visibility = View.VISIBLE
                }
                is ResponceState.Succes -> {
                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                    viewShops(it.result.shops)
                }
            }
        })

    }

    private fun viewShops(shops: List<Shop>) {
        if (shops.isNotEmpty()){
            bindiShopFragmentBinding.noShop.visibility=View.GONE
            val adapter = AllShopAdapter { data -> onClicked(data) }
            adapter.bind(shops)
            bindiShopFragmentBinding.adapter = adapter
            adapter.notifyDataSetChanged()

        }else{
            bindiShopFragmentBinding.noShop.visibility=View.VISIBLE
        }

    }

    private fun onClicked(data: Shop) {
        val bundle = Bundle()
        bundle.putSerializable("data", data.shop)
        bundle.putString("type", "update")
        findNavController().navigate(R.id.action_shopfragment_to_addUpdateFragment, bundle)
    }

    private fun showBottomDialog() {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        dialog.setContentView(R.layout.bottomsheet_login)

        val otp = dialog.findViewById<EditText>(R.id.otp)
        val number_edit = dialog.findViewById<EditText>(R.id.phone)
        val sendotp = dialog.findViewById<View>(R.id.sendopt)
        val submit = dialog.findViewById<View>(R.id.submit)

        sendotp?.setOnClickListener {
            if (!number_edit!!.text!!.isEmpty()) {
                sendOtp(number_edit.text.toString())
            } else {
                showToast("Enter your number")
            }
        }

        submit?.setOnClickListener {
            if (!otp!!.text!!.isEmpty()) {
                verifyOtp(otp.text.toString())
                dialog.dismiss()
            }
        }


        dialog.show()
    }

    private fun verifyOtp(otp: String) {
        Log.e("TAG", user_id.toString() )
        viemmodel.verifyOtp(otp, user_id.toString()).observe(requireActivity(), Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    showToast(it.message)
                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE

                }
                is ResponceState.Loading -> {
                    bindiShopFragmentBinding.progressCircular.visibility = View.VISIBLE
                }
                is ResponceState.Succes -> {
                    showToast("Login succesfull")
                    preferenceManger.saveId(it.result.data.id.toString())
                    viemmodel.getShops()
                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                }
            }
        })
    }

    private fun sendOtp(number: String) {
        viemmodel.sendOtp(number)
        viemmodel.sendLiveData.observe(requireActivity(), Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    showToast(it.message)
                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                }
                is ResponceState.Loading -> {
                    bindiShopFragmentBinding.progressCircular.visibility = View.VISIBLE
                }
                is ResponceState.Succes -> {
                    showToast("Send otp Succesfull")
                    user_id = it.result.data.user_id
                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        Log.e("TAG", "onResume: ")
    }

}
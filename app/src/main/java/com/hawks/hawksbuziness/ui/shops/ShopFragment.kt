package com.hawks.hawksbuziness.ui.shops

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.BottomsheetLoginBinding
import com.hawks.hawksbuziness.databinding.ShopFragmentBinding
import com.hawks.hawksbuziness.model.shops.Shop
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.ui.shops.adapter.AllShopAdapter
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.hideKeyboard
import com.hawks.hawksbuziness.utill.hideKeyboards
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var bindiShopFragmentBinding: ShopFragmentBinding
    val viemmodel: ShopViewmodel by viewModels<ShopViewmodel>()
    var user_id: Int? = null
    private lateinit var progressDialog: ProgressDialog

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
        progressDialog= ProgressDialog(requireActivity())
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

//        bindiShopFragmentBinding.appbar.addMore.visibility = View.GONE
        bindiShopFragmentBinding.appbar.addLogo.visibility = View.VISIBLE
        bindiShopFragmentBinding.appbar.addLogo.setOnClickListener {

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
//                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
                is ResponceState.Loading -> {
//                    bindiShopFragmentBinding.progressCircular.visibility = View.VISIBLE
                    progressDialog.show()
                }
                is ResponceState.Succes -> {
//                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                    progressDialog.dismiss()
                    viewShops(it.result.shops)
                }
            }
        })
        viemmodel.phonelivedata.observe(requireActivity(), Observer {
            if (it.length==10){
              hideKeyboards()

            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
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
        val binding:BottomsheetLoginBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.bottomsheet_login,container,false)
        binding.viewmodel=viemmodel
//        val binding= DataBindingUtil.setContentView<BottomsheetLoginBinding>(requireActivity(),R.layout.bottomsheet_login)
        dialog.setContentView(binding.root)

        val otp = dialog.findViewById<EditText>(R.id.otp)
        val number_edit = dialog.findViewById<EditText>(R.id.phone)
        val sendotp = dialog.findViewById<View>(R.id.sendopt)
        val submit = dialog.findViewById<View>(R.id.submit)

        sendotp?.setOnClickListener {
            if (!viemmodel.mobile.value.isNullOrBlank()) {
                Log.e("TAG", "not blank" )
                sendOtp(viemmodel.mobile.value.toString())
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
//                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                    progressDialog.dismiss()

                }
                is ResponceState.Loading -> {
                    progressDialog.show()
//                    bindiShopFragmentBinding.progressCircular.visibility = View.VISIBLE
                }
                is ResponceState.Succes -> {
                    showToast("Login succesfull")
                    preferenceManger.saveId(it.result.data.id.toString())
                    viemmodel.getShops()
//                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                    progressDialog.dismiss()
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
                    progressDialog.dismiss()
//                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                }
                is ResponceState.Loading -> {
//                    bindiShopFragmentBinding.progressCircular.visibility = View.VISIBLE
                    progressDialog.show()
                }
                is ResponceState.Succes -> {
                    showToast("Send otp Succesfull")
                    user_id = it.result.data.user_id
                    progressDialog.dismiss()
//                    bindiShopFragmentBinding.progressCircular.visibility = View.GONE
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
    }

}
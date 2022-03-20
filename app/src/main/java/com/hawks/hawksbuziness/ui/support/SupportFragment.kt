package com.hawks.hawksbuziness.ui.support

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hawks.hawksbuziness.databinding.FragmentSupportBinding
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.utill.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class SupportFragment : Fragment() {

     private val viewmodel:SupportViewmodel by viewModels<SupportViewmodel>()
    private lateinit var binding:FragmentSupportBinding
    private  var dialog: BottomSheetDialog?=null
    @Inject
    lateinit var preferenceManger: PreferenceManger

    var user_id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSupportBinding.inflate(inflater)
        MainActivity.fragmentName="SUPPORT"
        binding.click=ClickHandler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submit.setOnClickListener {
            raiseTicket()
        }

    }

    private fun raiseTicket() {
        if (!preferenceManger.getUserId().isNullOrEmpty()){
            viewmodel.raiseTicket(binding.title.text.toString(),binding.message.text.toString()).observe(requireActivity(),
                Observer {
                    when(it){
                        is ResponceState.Failiure -> {

                        }
                        is ResponceState.Loading -> {
                            Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                        }
                        is ResponceState.Succes -> {

                            showSnackBar(requireActivity().container)

                            binding.title.setText("")
                            binding.message.setText("")
                        }
                    }
                })
        }else{
            showBottomDialog()
        }

    }

    private fun showBottomDialog() {
        dialog = BottomSheetDialog(requireContext(), com.hawks.hawksbuziness.R.style.BottomSheetDialogTheme)
        dialog?.setContentView(com.hawks.hawksbuziness.R.layout.bottomsheet_login)

        val otp = dialog?.findViewById<EditText>(com.hawks.hawksbuziness.R.id.otp)
        val number_edit = dialog?.findViewById<EditText>(com.hawks.hawksbuziness.R.id.phone)
        val sendotp = dialog?.findViewById<View>(com.hawks.hawksbuziness.R.id.sendopt)
        val submit = dialog?.findViewById<View>(com.hawks.hawksbuziness.R.id.submit)

        sendotp?.setOnClickListener {
            if (!number_edit!!.text!!.isEmpty()&& number_edit.text.length==10) {
                sendOtp(number_edit.text.toString())
            } else {
                showToast("Enter a valid number")
            }
        }

        submit?.setOnClickListener {
            if (!otp!!.text!!.isEmpty()) {
                verifyOtp(otp.text.toString())
            }
        }

        dialog?.show()
    }

    private fun verifyOtp(otp: String) {
        viewmodel.verifyOtp(otp, user_id.toString()).observe(requireActivity(), Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    showToast(it.message)


                }
                is ResponceState.Loading -> {
                }
                is ResponceState.Succes -> {
                    showToast("Login succesfull")

                    preferenceManger.saveId(it.result.data.id.toString())

                    dialog?.let { it ->
                        it.dismiss()
                    }

                }
            }
        })
    }

    private fun sendOtp(number: String) {
        viewmodel.sendOtp(number)
        viewmodel.sendLiveData.observe(requireActivity(), Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    showToast(it.message)

                }
                is ResponceState.Loading -> {

                }
                is ResponceState.Succes -> {
                    showToast("Send otp Succesfull")
                    user_id = it.result.data.user_id

                }
            }
        })

    }

    inner class ClickHandler(){
        fun whatsapp(view: View){
            whatsApp()
        }

        fun dial(view: View){
            Dial()
        }

        fun email(view: View){
            email()
        }
    }



}
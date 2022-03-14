package com.hawks.hawksbuziness.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.FragmentProfileFragmentBinding
import com.hawks.hawksbuziness.model.profile.Auth
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels<ProfileViewModel>()
    private lateinit var binding: FragmentProfileFragmentBinding


    @Inject
    lateinit var preferenceManger: PreferenceManger
    private  var dialog: BottomSheetDialog?=null
    var user_id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileFragmentBinding.inflate(inflater)
        MainActivity.fragmentName = "PROFILE"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // calling data from local db
        if (!preferenceManger.getUserId().isNullOrEmpty()) {
            profileViewModel.getProfileData()
        } else {
            showBottomDialog()
        }

        observeData()

    }

    private fun saveToLocal(auth: Auth) {
        profileViewModel.saveData(auth)
    }

    private fun saveData(result: Auth) {
        binding.data = result

    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        val inflater = super.onGetLayoutInflater(savedInstanceState)
        val contextThemeWrapper: Context =
            ContextThemeWrapper(requireContext(), R.style.profile_page_theme)
        return inflater.cloneInContext(contextThemeWrapper)
    }

    private fun observeData() {

        /** observe data from server and save to local*/
        profileViewModel.response.observe(requireActivity(), Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Succes -> {
                    if (it.result.status != 0) {
                        saveToLocal(it.result.auth)
                    }


                }
            }
        })
        /** observing data from local and set to views*/
        profileViewModel.dbresponses.observe(requireActivity(), Observer {
            if (it == null) {
                profileViewModel.getProfile()
            } else {
                saveData(it)
            }
        })

    }
    private fun showBottomDialog() {
        dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        dialog?.setContentView(com.hawks.hawksbuziness.R.layout.bottomsheet_login)

        val otp = dialog?.findViewById<EditText>(com.hawks.hawksbuziness.R.id.otp)
        val number_edit = dialog?.findViewById<EditText>(com.hawks.hawksbuziness.R.id.phone)
        val sendotp = dialog?.findViewById<View>(com.hawks.hawksbuziness.R.id.sendopt)
        val submit = dialog?.findViewById<View>(com.hawks.hawksbuziness.R.id.submit)

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
            }
        }

        dialog?.show()
    }


    private fun verifyOtp(otp: String) {
        profileViewModel.verifyOtp(otp, user_id.toString()).observe(requireActivity(), Observer {
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
                    profileViewModel.getProfile()

                }
            }
        })
    }

    private fun sendOtp(number: String) {
        profileViewModel.sendOtp(number)
        profileViewModel.sendLiveData.observe(requireActivity(), Observer {
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


}
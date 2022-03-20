package com.hawks.hawksbuziness.ui.profile

import android.content.Context
import android.content.Intent
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
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.FragmentProfileFragmentBinding
import com.hawks.hawksbuziness.model.profile.Auth
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.EditActivity
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.ui.activity.SignupActivity
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
        binding.lifecycleOwner=this
        binding.viewmodel=profileViewModel
        MainActivity.fragmentName = "PROFILE"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            startActivity(Intent(requireContext(),SignupActivity::class.java))
        }
        binding.edit.setOnClickListener {
            startActivity(Intent(requireContext(),EditActivity::class.java))
        }


        observeData()

    }

    private fun saveToLocal(auth: Auth) {
        profileViewModel.saveData(auth)
    }

    private fun saveData(result: Auth) {
        profileViewModel.dob.value= result.dob ?: ""
        profileViewModel.email.value= result.email?:""
        profileViewModel.gender.value= result.gender?:""
        profileViewModel.image.value= result.image?:""
        profileViewModel.mobile.value= result.mobile?:""
        profileViewModel.name.value= result.name?:""
        profileViewModel.nationality.value= result.nationality?:""
        profileViewModel.place.value= result.place?:""
        profileViewModel.country.value= result.country?:""
        profileViewModel.referal.value= result.referral_code?:""



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

    }


    override fun onStart() {
        super.onStart()
        Log.e("TAG", "onStart: ", )
    }

    override fun onResume() {
        super.onResume()
        if (!preferenceManger.getUserId().isNullOrEmpty()) {
            profileViewModel.getProfileData().observe(requireActivity(), Observer {
                if (it == null) {
                    profileViewModel.getProfile()
                } else {
                    saveData(it)
                }
            })
            binding.login.visibility=View.GONE
            binding.edit.visibility=View.VISIBLE
        }else{
            binding.login.visibility=View.VISIBLE
            binding.edit.visibility=View.GONE
        }
    }




}
package com.nithi.hawksbuziness.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nithi.hawksbuziness.R
import com.nithi.hawksbuziness.databinding.FragmentProfileFragmentBinding
import com.nithi.hawksbuziness.model.profile.Auth
import com.nithi.hawksbuziness.model.profile.profile
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel:ProfileViewModel by viewModels<ProfileViewModel> ()
    private lateinit var binding:FragmentProfileFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        profileViewModel.getProfile()

        profileViewModel.response.observe(requireActivity(), Observer {
            when(it){
                is ResponceState.Failiure -> {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Succes -> {
                    saveToLocal(it.result.auth)

                }
            }
        })
        profileViewModel.getProfileData()
        profileViewModel.dbresponses.observe(requireActivity(), Observer {

            Log.e("TAG", "onViewCreated: $it")
            if (it==null){
                profileViewModel.getProfile()
            }else{
                saveData(it)
            }
        })
    }

    private fun saveToLocal(auth: Auth) {
        profileViewModel.saveData(auth)
    }

    private fun saveData(result: Auth) {
        binding.data=result

    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        val inflater = super.onGetLayoutInflater(savedInstanceState)
        val contextThemeWrapper: Context = ContextThemeWrapper(requireContext(), R.style.profile_page_theme)
        return inflater.cloneInContext(contextThemeWrapper)
    }


}
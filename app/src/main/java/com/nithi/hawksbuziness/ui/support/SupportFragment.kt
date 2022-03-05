package com.nithi.hawksbuziness.ui.support

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.nithi.hawksbuziness.R
import com.nithi.hawksbuziness.databinding.FragmentSupportBinding
import com.nithi.hawksbuziness.utill.ResponceState
import com.nithi.hawksbuziness.utill.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class SupportFragment : Fragment() {

     private val viewmodel:SupportViewmodel by viewModels<SupportViewmodel>()
    private lateinit var binding:FragmentSupportBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSupportBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submit.setOnClickListener {
            raiseTicket()
        }

    }

    private fun raiseTicket() {
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
    }



}
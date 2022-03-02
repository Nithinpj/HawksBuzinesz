package com.nithi.hawksbuziness.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.nithi.hawksbuziness.R
import com.nithi.hawksbuziness.databinding.ActivityReferalBinding
import com.nithi.hawksbuziness.model.country.Country
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReferalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReferalBinding
    private val viewModel:ReferenceViewModel by viewModels<ReferenceViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= DataBindingUtil.setContentView(this,R.layout.activity_referal)
        binding.submit.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        viewModel.getCountries()

        viewModel.response.observe(this, Observer {
            when(it){
                is ResponceState.Failiure -> Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                is ResponceState.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                is ResponceState.Succes ->{
                    setData(it)
                }
            }
        })

        viewModel.getLanguages()

        viewModel.response_language.observe(this, Observer {
            when(it){
                is ResponceState.Failiure -> ""
                is ResponceState.Loading -> ""
                is ResponceState.Succes -> binding.language.text=it.result.data[0].language
            }
        })
    }

    private fun setData(it: ResponceState.Succes<Country>) {
        val  adapter=ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,it.result.data)
        binding.adapter=adapter
    }
}
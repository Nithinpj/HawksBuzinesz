package com.hawks.hawksbuziness.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.ActivityReferalBinding
import com.hawks.hawksbuziness.model.country.Country
import com.hawks.hawksbuziness.model.country.CountryLocal
import com.hawks.hawksbuziness.model.country.Data
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.isLocationPermissionGranted
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReferalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReferalBinding
    private val viewModel:ReferenceViewModel by viewModels<ReferenceViewModel>()

    @Inject
     lateinit var preferenceManger: PreferenceManger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_referal)
        binding.click=Clickhandler()
        binding.submit.setOnClickListener {
            if (binding.terms.isChecked){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else {
                Toast.makeText(this, "Accept terms of service", Toast.LENGTH_SHORT).show()
            }

        }

        binding.termsText.setOnClickListener {
            startActivity(Intent(this,TermsAndConditions::class.java))
        }
//        viewModel.getCountries()
//
//        viewModel.response.observe(this, Observer {
//            when(it){
//                is ResponceState.Failiure -> Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
//                is ResponceState.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
//                is ResponceState.Succes ->{
//                    setData(it)
//                }
//            }
//        })
//
//        viewModel.getLanguages()
//
//        viewModel.response_language.observe(this, Observer {
//            when(it){
//                is ResponceState.Failiure -> ""
//                is ResponceState.Loading -> ""
//                is ResponceState.Succes -> binding.language.text=it.result.data[0].language
//            }
//        })
//

        //checking for location granted or not
       isLocationPermissionGranted()

        setData()
    }



    private fun setData() {
        val countryLocal1=CountryLocal()
        countryLocal1.id=1
        countryLocal1.name="India"

        val countryLocal2=CountryLocal()
        countryLocal2.id=2
        countryLocal2.name="Dubai"

        var datas:ArrayList<CountryLocal> = arrayListOf(countryLocal1,countryLocal2)




        val  adapter=ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,datas)
        binding.adapter=adapter
    }

    inner class Clickhandler{
        fun click(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent!!.selectedItem as CountryLocal
            preferenceManger.saveCountry(item.id.toString())
            preferenceManger.saveLanguage("English")
        }
    }

}


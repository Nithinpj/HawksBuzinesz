package com.hawks.hawksbuziness.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.ActivitySignupBinding
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.profile.ProfileViewModel
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private val profileViewModel by viewModels<ProfileViewModel>()
    lateinit var binding:ActivitySignupBinding
    var user_id: Int? = null

    @Inject
    lateinit var preferenceManger:PreferenceManger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_signup)
        initializeData()
    }

    private fun initializeData() {
        binding.otp.setOnClickListener {
            if (binding.phoneNumber.text.isNotEmpty()) {
                sendOtp(binding.phoneNumber.text.toString())
            } else {
                Toast.makeText(this, "Enter a valid no", Toast.LENGTH_SHORT).show()
            }
        }
        binding.submit.setOnClickListener {
            if (binding.otpText.text.isNotEmpty()){
                verifyOtp(binding.otpText.text.toString())
            }else{
                Toast.makeText(this, "Enter your otp number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verifyOtp(otp: String) {
        profileViewModel.verifyOtp(otp, user_id.toString()).observe(this, Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    Toast.makeText(this, "Verification failed", Toast.LENGTH_SHORT).show()
                }
                is ResponceState.Loading -> {
                }
                is ResponceState.Succes -> {
                    Toast.makeText(this, "Login succesfull", Toast.LENGTH_SHORT).show()
                    if (it.result.data!=null){
                        preferenceManger.saveId(it.result.data.id.toString())
                        finish()
                    }else{
                        Toast.makeText(this, "Otp is not valid", Toast.LENGTH_SHORT).show()
                    }




                }
            }
        })
    }

    private fun sendOtp(number: String) {
        Log.e("TAG",number)
        profileViewModel.sendOtp(number.trim())
        profileViewModel.sendLiveData.observe(this, Observer {
            when (it) {
                is ResponceState.Failiure -> {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                }
                is ResponceState.Loading -> {

                }
                is ResponceState.Succes -> {
                    Toast.makeText(this, "Send otp Succesfull", Toast.LENGTH_SHORT).show()
                    user_id = it.result.data.user_id

                }
            }
        })

    }
}
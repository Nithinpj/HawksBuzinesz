package com.hawks.hawksbuziness.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawks.hawksbuziness.local.dao.ProfileDao
import com.hawks.hawksbuziness.model.otp.send.Sendotp
import com.hawks.hawksbuziness.model.otp.verify.VerifyOtp
import com.hawks.hawksbuziness.model.profile.Auth
import com.hawks.hawksbuziness.model.profile.profile
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.repositarory.BusinessRepository
import com.hawks.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val respository: BusinessRepository, val preferenceManger: PreferenceManger,
    val dao: ProfileDao
) : ViewModel() {
    private val _response: MutableLiveData<ResponceState<profile>> = MutableLiveData()
    val response: LiveData<ResponceState<profile>> = _response

     val _dbresponse:MutableLiveData<Auth> = MutableLiveData()
    var dbresponses:LiveData<Auth> =_dbresponse;

    fun getProfile() = viewModelScope.launch {
        respository.getProfile(preferenceManger.getUserId()).collect {
            _response.value = it;
        }
    }
     fun saveData(auth: Auth)=viewModelScope.launch {
        dao.insertProfole(auth)
    }

     fun getProfileData()=viewModelScope.launch {
      dbresponses=dao.getProfile()
    }

    private val sendMutableLiveData:MutableLiveData<ResponceState<Sendotp>> =MutableLiveData()
    val sendLiveData: LiveData<ResponceState<Sendotp>> = sendMutableLiveData
    fun sendOtp(number: String) {
        viewModelScope.launch {
            respository.sendOtp(number).collect {
                sendMutableLiveData.value=it
            }
        }
    }

    fun verifyOtp(otp:String,user_id:String):MutableLiveData<ResponceState<VerifyOtp>>{
        val mutableLiveData = MutableLiveData<ResponceState<VerifyOtp>>()
        viewModelScope.launch {
            respository.verifyOtp(otp = otp,userId = user_id).collect {
                mutableLiveData.value = it
            }
        }
        return mutableLiveData
    }
}
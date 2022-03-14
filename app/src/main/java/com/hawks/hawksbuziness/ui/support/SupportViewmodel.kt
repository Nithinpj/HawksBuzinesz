package com.hawks.hawksbuziness.ui.support

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawks.hawksbuziness.model.otp.send.Sendotp
import com.hawks.hawksbuziness.model.otp.verify.VerifyOtp
import com.hawks.hawksbuziness.model.ticket.Ticket
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.repositarory.BusinessRepository
import com.hawks.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportViewmodel @Inject constructor(val repository: BusinessRepository,
val preferenceManger: PreferenceManger) :ViewModel() {
    fun raiseTicket(title:String,message:String):MutableLiveData<ResponceState<Ticket>>{
        val mutableLiveData:MutableLiveData<ResponceState<Ticket>> = MutableLiveData()
        viewModelScope.launch {
            repository.raiseTicket(preferenceManger.getUserId(),title,message).collect {
                mutableLiveData.value=it
            }
        }
        return mutableLiveData
    }

    private val sendMutableLiveData:MutableLiveData<ResponceState<Sendotp>> =MutableLiveData()
    val sendLiveData: LiveData<ResponceState<Sendotp>> = sendMutableLiveData
    fun sendOtp(number: String) {
        viewModelScope.launch {
            repository.sendOtp(number).collect {
                sendMutableLiveData.value=it
            }
        }
    }

    fun verifyOtp(otp:String,user_id:String):MutableLiveData<ResponceState<VerifyOtp>>{
        val mutableLiveData = MutableLiveData<ResponceState<VerifyOtp>>()
        viewModelScope.launch {
            repository.verifyOtp(otp = otp,userId = user_id).collect {
                mutableLiveData.value = it
            }
        }
        return mutableLiveData
    }
}
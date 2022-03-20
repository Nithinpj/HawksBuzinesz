package com.hawks.hawksbuziness.ui.profile

import androidx.databinding.Bindable
import androidx.databinding.Observable
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
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val respository: BusinessRepository, val preferenceManger: PreferenceManger,
    val dao: ProfileDao
) : ViewModel(), Observable {
    private val _response: MutableLiveData<ResponceState<profile>> = MutableLiveData()
    val response: LiveData<ResponceState<profile>> = _response


    fun getProfile() = viewModelScope.launch {
        respository.getProfile(preferenceManger.getUserId()).collect {
            _response.value = it;
        }
    }

    fun saveData(auth: Auth) = viewModelScope.launch {
        dao.insertProfole(auth)
    }

    fun updateProfile(auth: Auth)=viewModelScope.launch {
        dao.updateProfile(auth)
    }
    fun clearProfile(){
       viewModelScope.launch {
           dao.clearProfile()
       }
    }

    fun getProfileData(): LiveData<Auth> = dao.getProfile()


    private val sendMutableLiveData: MutableLiveData<ResponceState<Sendotp>> = MutableLiveData()
    val sendLiveData: LiveData<ResponceState<Sendotp>> = sendMutableLiveData
    fun sendOtp(number: String) {
        viewModelScope.launch {
            respository.sendOtp(number).collect {
                sendMutableLiveData.value = it
            }
        }
    }

    fun verifyOtp(otp: String, user_id: String): MutableLiveData<ResponceState<VerifyOtp>> {
        val mutableLiveData = MutableLiveData<ResponceState<VerifyOtp>>()
        viewModelScope.launch {
            respository.verifyOtp(otp = otp, userId = user_id).collect {
                mutableLiveData.value = it
            }
        }
        return mutableLiveData
    }

    @Bindable
    val dob = MutableLiveData<String>()

    @Bindable
    val email = MutableLiveData<String>()

    @Bindable
    val gender = MutableLiveData<String>()

    @Bindable
    val image = MutableLiveData<String>()

    @Bindable
    val mobile = MutableLiveData<String>()

    @Bindable
    val name = MutableLiveData<String>()

    @Bindable
    val nationality = MutableLiveData<String>()

    @Bindable
    val place = MutableLiveData<String>()

    @Bindable
    val country = MutableLiveData<String>()

    @Bindable
    val referal = MutableLiveData<String>()

    fun updateProfileData(hashMap: HashMap<String, String>) {
        viewModelScope.launch {
            respository.updateProfile(hashMap).collect{
                _response.value = it;
            }
        }
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
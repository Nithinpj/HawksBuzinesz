package com.nithi.hawksbuziness.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.hawksbuziness.local.dao.ProfileDao
import com.nithi.hawksbuziness.model.profile.Auth
import com.nithi.hawksbuziness.model.profile.profile
import com.nithi.hawksbuziness.preferences.PreferenceManger
import com.nithi.hawksbuziness.repositarory.BusinessRepository
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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
}
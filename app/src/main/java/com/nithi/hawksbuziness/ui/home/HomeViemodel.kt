package com.nithi.hawksbuziness.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.hawksbuziness.model.SignUp
import com.nithi.hawksbuziness.model.web.Web
import com.nithi.hawksbuziness.repositarory.BusinessRepository
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViemodel @Inject constructor(val repository: BusinessRepository) :ViewModel(){
    val _response:MutableLiveData<ResponceState<Web>> = MutableLiveData()
    val response:LiveData<ResponceState<Web>> = _response

    fun getWeb()=viewModelScope.launch {
        repository.getWebData().collect {
            _response.value=it
        }
    }

}
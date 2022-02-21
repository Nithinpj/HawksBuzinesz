package com.nithi.hawksbuziness.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.hawksbuziness.model.SignUp
import com.nithi.hawksbuziness.repositarory.BusinessRepository
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViemodel @Inject constructor(val repository: BusinessRepository) :ViewModel(){
    val mutableLiveData:MutableLiveData<String> = MutableLiveData()
    fun signUp():MutableLiveData<String>{
        viewModelScope.launch {
            repository.getData().collect {
                when(it){
                    is ResponceState.Error -> TODO()
                    is ResponceState.Failed -> TODO()
                    is ResponceState.Loading ->{
                        mutableLiveData.value=it.message
                    }
                    is ResponceState.Success -> TODO()
                }
            }
        }
        return mutableLiveData;
    }
}
package com.nithi.hawksbuziness.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.hawksbuziness.model.country.Country
import com.nithi.hawksbuziness.model.languages.Language
import com.nithi.hawksbuziness.repositarory.BusinessRepository
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReferenceViewModel @Inject constructor(val repositary:BusinessRepository):ViewModel() {

    private val _response:MutableLiveData<ResponceState<Country>> = MutableLiveData()
    val response:LiveData<ResponceState<Country>> = _response
    fun getCountries(){
        viewModelScope.launch {
            repositary.getCountries().collect {
                _response.value=it
            }
        }

    }

    private val _response_language:MutableLiveData<ResponceState<Language>> = MutableLiveData()
    val  response_language:LiveData<ResponceState<Language>> =_response_language

    fun getLanguages()=viewModelScope.launch {
        repositary.getLanguages().collect{
            _response_language.value=it
        }
    }
}
package com.nithi.hawksbuziness.ui.support

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.hawksbuziness.model.ticket.Ticket
import com.nithi.hawksbuziness.preferences.PreferenceManger
import com.nithi.hawksbuziness.repositarory.BusinessRepository
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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
}
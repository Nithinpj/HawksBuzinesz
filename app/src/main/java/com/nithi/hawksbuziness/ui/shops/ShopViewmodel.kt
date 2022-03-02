package com.nithi.hawksbuziness.ui.shops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.hawksbuziness.model.shops.Shop
import com.nithi.hawksbuziness.model.shops.Shops
import com.nithi.hawksbuziness.preferences.PreferenceManger
import com.nithi.hawksbuziness.repositarory.BusinessRepository
import com.nithi.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewmodel @Inject constructor(val repository: BusinessRepository,val preferenceManger: PreferenceManger):ViewModel() {

    private val _shopMutableLiveData:MutableLiveData<ResponceState<Shops>> = MutableLiveData()
    val shopMutableLiveData:LiveData<ResponceState<Shops>> =_shopMutableLiveData
    fun getShops()=viewModelScope.launch {
        repository.getShops(preferenceManger.getUserId()).collect {
            _shopMutableLiveData.value=it
        }
    }
}
package com.hawks.hawksbuziness.ui.shops

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawks.hawksbuziness.local.dao.CategoryDao
import com.hawks.hawksbuziness.model.category.Data
import com.hawks.hawksbuziness.model.otp.send.Sendotp
import com.hawks.hawksbuziness.model.otp.verify.VerifyOtp
import com.hawks.hawksbuziness.model.shop.add.AddShop
import com.hawks.hawksbuziness.model.shop.update.UpdateData
import com.hawks.hawksbuziness.model.shops.Shops
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.repositarory.BusinessRepository
import com.hawks.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewmodel @Inject constructor(
    val repository: BusinessRepository,
    val preferenceManger: PreferenceManger,
    val dao: CategoryDao
) : ViewModel(), Observable {

    private val _shopMutableLiveData: MutableLiveData<ResponceState<Shops>> = MutableLiveData()
    val testData = MutableLiveData<ResponceState<Shops>>()
    val shopMutableLiveData: LiveData<ResponceState<Shops>> = _shopMutableLiveData
    fun getShops() = viewModelScope.launch {
        repository.getShops(preferenceManger.getUserId()).collect {
            _shopMutableLiveData.value = it
            testData.value = it
        }
    }

    @Bindable
    val data: MutableLiveData<com.hawks.hawksbuziness.model.shop.Shop>? = MutableLiveData()

    @Bindable
    val shop_name = MutableLiveData<String>()

    @Bindable
    val address = MutableLiveData<String>()

    @Bindable
    val contactNo = MutableLiveData<String>()

    @Bindable
    val branch = MutableLiveData<String>()

    @Bindable
    val email = MutableLiveData<String>()

    @Bindable
    val person_incharge = MutableLiveData<String>()

    @Bindable
    val office_contact = MutableLiveData<String>()

    @Bindable
    val gstn = MutableLiveData<String>()

    @Bindable
    val trade_mark = MutableLiveData<Boolean>()

    @Bindable
    val private_limited = MutableLiveData<Boolean>()

    @Bindable
    val whatsapp = MutableLiveData<String>()

    @Bindable
    val facebook = MutableLiveData<String>()

    @Bindable
    val instagram = MutableLiveData<String>()

    @Bindable
    val linkedin = MutableLiveData<String>()

    @Bindable
    val multistore = MutableLiveData<Boolean>()

    @Bindable
    val head_office = MutableLiveData<Boolean>()

    @Bindable
    val multistore_name = MutableLiveData<String>()

    @Bindable
    val webisite = MutableLiveData<String>()

    fun getAllCategories(): LiveData<List<Data>> {
        return dao.getAllCategories()
    }

    //add business data
    private val _addMutableLiveData: MutableLiveData<ResponceState<AddShop>> = MutableLiveData()
    val addLiveData: LiveData<ResponceState<AddShop>> = _addMutableLiveData
    fun addBusiness(hashMap: HashMap<String, String>) = viewModelScope.launch {
        repository.addShop(hashMap).collect {
            _addMutableLiveData.value = it
        }
    }
    // update business data

    private val _updateMutableLiveData: MutableLiveData<ResponceState<UpdateData>> =
        MutableLiveData()
    val updateLiveData: LiveData<ResponceState<UpdateData>> = _updateMutableLiveData
    fun updateBusiness(hashMap: HashMap<String, String>) = viewModelScope.launch {
        repository.updateShop(hashMap).collect {
            _updateMutableLiveData.value = it
        }
    }

    private val sendMutableLiveData:MutableLiveData<ResponceState<Sendotp>> =MutableLiveData()
    val sendLiveData:LiveData<ResponceState<Sendotp>> = sendMutableLiveData
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


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}
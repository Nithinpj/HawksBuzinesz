package com.hawks.hawksbuziness.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawks.hawksbuziness.local.dao.CategoryDao
import com.hawks.hawksbuziness.model.category.Categories
import com.hawks.hawksbuziness.model.category.Data
import com.hawks.hawksbuziness.model.web.Web
import com.hawks.hawksbuziness.repositarory.BusinessRepository
import com.hawks.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViemodel @Inject constructor(val repository: BusinessRepository,val dao:CategoryDao) :ViewModel(){
    val _response:MutableLiveData<ResponceState<Web>> = MutableLiveData()
    val response:LiveData<ResponceState<Web>> = _response

    fun getWeb()=viewModelScope.launch {
        repository.getWebData().collect {
            _response.value=it
        }
    }

    fun insertCategoryData(categories:List<Data>)=viewModelScope.launch {
        dao.deleteAll()
        dao.insertCategory(categories)
    }

    fun getAllCategories():LiveData<List<Data>>{
        return dao.getAllCategories()
    }

    val _responseCategory:MutableLiveData<ResponceState<Categories>> = MutableLiveData()
    val responseCategory:LiveData<ResponceState<Categories>> = _responseCategory

    fun getCategories()=viewModelScope.launch {
        repository.getCategories().collect{
           _responseCategory.value=it
        }
    }

}
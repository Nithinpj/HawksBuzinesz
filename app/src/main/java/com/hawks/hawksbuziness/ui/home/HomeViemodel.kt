package com.hawks.hawksbuziness.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hawks.hawksbuziness.local.dao.CategoryDao
import com.hawks.hawksbuziness.local.dao.LangugeDao
import com.hawks.hawksbuziness.local.dao.PlaceDao
import com.hawks.hawksbuziness.model.category.Categories
import com.hawks.hawksbuziness.model.category.Data
import com.hawks.hawksbuziness.model.languages.Language
import com.hawks.hawksbuziness.model.places.Places
import com.hawks.hawksbuziness.model.web.Web
import com.hawks.hawksbuziness.repositarory.BusinessRepository
import com.hawks.hawksbuziness.utill.ResponceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViemodel @Inject constructor(
    val repository: BusinessRepository,
    val dao: CategoryDao,
    val placeDao: PlaceDao,
    val languageDao:LangugeDao
) : ViewModel() {
    private val _response: MutableLiveData<ResponceState<Web>> = MutableLiveData()
    val response: LiveData<ResponceState<Web>> = _response

    fun getWeb() = viewModelScope.launch {
        repository.getWebData().collect {
            _response.value = it
        }
    }

    fun insertCategoryData(categories: List<Data>) = viewModelScope.launch {
        dao.deleteAll()
        dao.insertCategory(categories)
    }

    fun getAllCategories(): LiveData<List<Data>> {
        return dao.getAllCategories()
    }

    private val _responseCategory: MutableLiveData<ResponceState<Categories>> = MutableLiveData()
    val responseCategory: LiveData<ResponceState<Categories>> = _responseCategory

    fun getCategories() = viewModelScope.launch {
        repository.getCategories().collect {
            _responseCategory.value = it
        }
    }

    private val _placeMutableLiveData=MutableLiveData<ResponceState<Places>>()
    val placeLiveData:LiveData<ResponceState<Places>> = _placeMutableLiveData

    fun getPlaces() = viewModelScope.launch {
        repository.getPlaces().collect {
            _placeMutableLiveData.value=it
        }
    }

    fun insertPlaces( listplaces:List<com.hawks.hawksbuziness.model.places.Data>)=viewModelScope.launch {
        placeDao.insertPlace(listplaces)
    }


    private val _response_language:MutableLiveData<ResponceState<Language>> = MutableLiveData()
    val  response_language:LiveData<ResponceState<Language>> =_response_language

    fun getLanguages()=viewModelScope.launch {
        repository.getLanguages().collect{
            _response_language.value=it
        }
    }

    fun insertLanguage(listLanguage:List<com.hawks.hawksbuziness.model.languages.Data>)=viewModelScope.launch {
        languageDao.insertLanguage(listLanguage)
    }

    fun getAllLanguages():LiveData<List<com.hawks.hawksbuziness.model.languages.Data>>{
        return languageDao.getAllLanguges()
    }


}
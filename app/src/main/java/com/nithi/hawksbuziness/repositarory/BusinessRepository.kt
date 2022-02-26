package com.nithi.hawksbuziness.repositarory
import com.nithi.hawksbuziness.model.country.Country
import com.nithi.hawksbuziness.model.SignUp
import com.nithi.hawksbuziness.model.languages.Language
import com.nithi.hawksbuziness.model.web.Web
import com.nithi.hawksbuziness.services.AuthApi
import com.nithi.hawksbuziness.utill.BaseApiResponse
import com.nithi.hawksbuziness.utill.ResponceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BusinessRepository @Inject constructor(private val authApi: AuthApi):BaseApiResponse() {

    fun getData():Flow<ResponceState<SignUp>>{
        return flow<ResponceState<SignUp>> {
            emit(ResponceState.Loading("Loading"))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCountries():Flow<ResponceState<Country>>{
        return flow<ResponceState<Country>> {
            emit(ResponceState.Loading("Loadng"))
            emit(safeApi { authApi.getContries() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getLanguages():Flow<ResponceState<Language>>{
        return flow<ResponceState<Language>> {
            emit(ResponceState.Loading("Loadng"))
            emit(safeApi { authApi.getLanguages() })
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getWebData():Flow<ResponceState<Web>>{
        return flow<ResponceState<Web>> {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.getWeb() })
        }.flowOn(Dispatchers.IO)
    }

}
package com.nithi.hawksbuziness.repositarory
import com.nithi.hawksbuziness.model.SignUp
import com.nithi.hawksbuziness.services.AuthApi
import com.nithi.hawksbuziness.utill.ResponceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BusinessRepository @Inject constructor(private val authApi: AuthApi) {

    fun getData():Flow<ResponceState<SignUp>>{
        return flow<ResponceState<SignUp>> {
            emit(ResponceState.Loading("Loading"))
        }.flowOn(Dispatchers.IO)
    }

}
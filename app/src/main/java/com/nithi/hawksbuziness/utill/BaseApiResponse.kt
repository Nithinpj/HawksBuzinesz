package com.nithi.hawksbuziness.utill

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException

abstract class BaseApiResponse {
    suspend fun <T> safeApi(apiCall: suspend () -> T): ResponceState<T> =
        withContext(Dispatchers.IO) {
            try {
                ResponceState.Succes(apiCall.invoke())
            } catch (t: Throwable) {
                when (t) {

                    is SocketTimeoutException -> ResponceState.Failiure(false, 408, null,"time out")
                    is HttpException -> {
                        ResponceState.Failiure(false, t.code(),t.response()?.errorBody(),t.message())
                    }
                    else -> {
                        ResponceState.Failiure(true, null, null,"no network")
                    }
                }
            }
        }
}
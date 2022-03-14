package com.hawks.hawksbuziness.utill

import okhttp3.ResponseBody

sealed class ResponceState<out T> {
    data class Loading<T>(val message: String) : ResponceState<T>()
    data class Succes<out T>(val result: T) : ResponceState<T>()
    data class Failiure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val message: String
    ) : ResponceState<Nothing>()
}
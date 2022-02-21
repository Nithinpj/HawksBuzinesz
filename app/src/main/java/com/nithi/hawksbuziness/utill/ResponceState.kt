package com.nithi.hawksbuziness.utill

sealed class ResponceState<T>{
    data class Loading<T>(val message:String):ResponceState<T>()
    data class Success<T>(val  data:T):ResponceState<T>()
    data class Failed<T>(val message:String):ResponceState<T>()
    data class Error<T> (val code: Int) : ResponceState<T>()
}
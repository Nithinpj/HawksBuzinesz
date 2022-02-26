package com.nithi.hawksbuziness.services

import com.nithi.hawksbuziness.model.country.Country
import com.nithi.hawksbuziness.model.languages.Language
import com.nithi.hawksbuziness.model.web.Web
import retrofit2.http.GET

interface AuthApi {
    @GET("common/getcountries")
    suspend fun getContries(): Country
    @GET("common/getlanguages")
    suspend fun getLanguages(): Language
    @GET("common/getweburl")
    suspend fun getWeb(): Web
}
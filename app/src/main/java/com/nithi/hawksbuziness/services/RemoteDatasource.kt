package com.nithi.hawksbuziness.services

import com.nithi.hawksbuziness.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoteDatasource @Inject constructor(){

    private val loggingInterceptor=HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client=OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                this.addInterceptor(loggingInterceptor)
            }
            this.connectTimeout(30, TimeUnit.SECONDS)
            this.readTimeout(30, TimeUnit.SECONDS)
            this.writeTimeout(30, TimeUnit.SECONDS)
        }.build()

    fun<API>buildApi(api:Class<API>):API{
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build().create(api)
    }
}
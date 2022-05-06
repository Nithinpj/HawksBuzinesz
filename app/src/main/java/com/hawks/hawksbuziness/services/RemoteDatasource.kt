package com.hawks.hawksbuziness.services

import com.hawks.hawksbuziness.BuildConfig
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
            this.connectTimeout(1, TimeUnit.MINUTES)
            this.readTimeout(1, TimeUnit.MINUTES)
            this.writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor {chain->
                    val request = chain.request().newBuilder().addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ik5pZ2hpbCIsImlhdCI6MTUxNjIzOTAyMn0.bIs9yMJTc868M9paBXwMEwCNsykhAYo7yYp_DA7BlnM").build()
                    chain.proceed(request)
                }
        }.build()

    fun<API>buildApi(api:Class<API>):API{
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build().create(api)
    }
}

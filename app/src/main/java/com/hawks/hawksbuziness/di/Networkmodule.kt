package com.hawks.hawksbuziness.di

import com.hawks.hawksbuziness.services.AuthApi
import com.hawks.hawksbuziness.services.RemoteDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Networkmodule {
    //module injection
    @Provides
    fun provideService(remoteDatasource: RemoteDatasource):AuthApi{
        return remoteDatasource.buildApi(AuthApi::class.java)
    }
}
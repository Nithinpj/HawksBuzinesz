package com.nithi.hawksbuziness.di

import com.nithi.hawksbuziness.services.AuthApi
import com.nithi.hawksbuziness.services.RemoteDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Networkmodule {
    @Provides
    fun provideService(remoteDatasource: RemoteDatasource):AuthApi{
        return remoteDatasource.buildApi(AuthApi::class.java)
    }
}
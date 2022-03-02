package com.nithi.hawksbuziness.di

import android.content.Context
import com.nithi.hawksbuziness.local.HawksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):HawksDatabase= HawksDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideProfileDao(database: HawksDatabase)=database.profileDao()
}
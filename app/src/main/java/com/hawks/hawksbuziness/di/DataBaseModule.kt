package com.hawks.hawksbuziness.di

import android.content.Context
import com.hawks.hawksbuziness.local.HawksDatabase
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

    @Singleton
    @Provides
    fun provideCategoryDao(database: HawksDatabase)=database.categoryDao()

    @Singleton
    @Provides
    fun providePlaceDao(database: HawksDatabase)=database.placeDao()
}
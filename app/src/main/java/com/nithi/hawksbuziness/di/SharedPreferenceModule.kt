package com.nithi.hawksbuziness.di

import android.content.Context
import android.content.SharedPreferences
import com.nithi.hawksbuziness.preferences.PreferenceManger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context)=context.getSharedPreferences(
        "hawsksbusiness",Context.MODE_PRIVATE
    )

    @Singleton
    @Provides
    fun provideSessionManager(sharedPreferences: SharedPreferences)= PreferenceManger(sharedPreferences)

}
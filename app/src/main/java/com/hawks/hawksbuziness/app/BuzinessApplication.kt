package com.hawks.hawksbuziness.app

import android.app.Application
import com.hawks.hawksbuziness.utill.RemoteConfigUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BuzinessApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        RemoteConfigUtils.init()
    }
}
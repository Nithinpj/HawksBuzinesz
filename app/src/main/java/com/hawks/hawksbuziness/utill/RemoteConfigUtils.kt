package com.hawks.hawksbuziness.utill

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

object RemoteConfigUtils {
    private const val TAG = "RemoteConfigUtils"
    private const val ISAPPVISIBLE="visibility"

    private val DEFAULTS: HashMap<String, Any> = hashMapOf(
        ISAPPVISIBLE to true
    )

    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun init() {
        remoteConfig = FirebaseRemoteConfig()
    }

    fun FirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings =
            FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0).build()

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(DEFAULTS)

        remoteConfig.fetchAndActivate().addOnSuccessListener {
            Log.d(TAG, "getFirebaseRemoteConfig: ")
        }
        return remoteConfig
    }

    fun isAppvisible():Boolean= remoteConfig.getBoolean(ISAPPVISIBLE)

}
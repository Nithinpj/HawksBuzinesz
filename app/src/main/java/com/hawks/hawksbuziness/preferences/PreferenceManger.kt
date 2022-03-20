package com.hawks.hawksbuziness.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManger @Inject constructor(val sharedPreferences: SharedPreferences) {
    companion object {
        const val URL = "URL"
        const val USER_ID = "USERID"
        const val LATITUDE = "LATITUDE"
        const val LONGITUDE = "LONGITUDE"
        const val COUNTRY = "COUNTRY"
    }

    private fun setPreferenceData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveUrl(value: String) {
        setPreferenceData(URL, value)
    }

    fun getUrl(): String = sharedPreferences.getString(URL, "").toString()

    fun saveId(value: String) {
        setPreferenceData(USER_ID, value)
    }

    fun getUserId(): String = sharedPreferences.getString(USER_ID, "").toString()

    fun saveLat(value: String) {
        setPreferenceData(LATITUDE, value)
    }

    fun getLat(): String = sharedPreferences.getString(LATITUDE, "").toString()

    fun saveLon(value: String) {
        setPreferenceData(LONGITUDE, value)
    }

    fun getLon(): String = sharedPreferences.getString(LONGITUDE, "").toString()

    fun saveCountry(value: String){
        setPreferenceData(COUNTRY,value)
    }

    fun getCountry():String?=sharedPreferences.getString(COUNTRY,"").toString()


    fun removeAll() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()

    }
}


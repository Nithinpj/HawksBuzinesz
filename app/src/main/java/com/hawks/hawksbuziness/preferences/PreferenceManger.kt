package com.hawks.hawksbuziness.preferences

import android.animation.ValueAnimator
import android.content.SharedPreferences
import java.lang.NullPointerException
import javax.inject.Inject

class PreferenceManger @Inject constructor(val sharedPreferences: SharedPreferences) {
    companion object {
        const val URL = "URL"
        const val USER_ID = "USERID"
        const val LATITUDE = "LATITUDE"
        const val LONGITUDE = "LONGITUDE"
        const val COUNTRY = "COUNTRY"
        const val PLACE="PLACE"
        const val LANGUGAGE="LANGUAGE"
        const val TERMS="TERMS"
        const val PRIVACY="PRIVACY"
        const val VERSION="VERSION"
        const val NUMBER="NUMBER"
        const val WANUMBER="WANUMBER"
        const val EMAIL="EMAIL"
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

    fun savePlace(value: String) =setPreferenceData(PLACE,value)
    fun getPlace():String?=sharedPreferences.getString(PLACE,"").toString()

    fun saveLanguage(value: String)=setPreferenceData(LANGUGAGE,value)
    fun getLangugae():String=sharedPreferences.getString(LANGUGAGE,"").toString()

    fun saveTerms(value: String)=setPreferenceData(TERMS,value)
    fun getTerms():String=sharedPreferences.getString(TERMS,"").toString()

    fun savePrivacy(value: String)=setPreferenceData(PRIVACY,value)
    fun getPrivacy():String=sharedPreferences.getString(PRIVACY,"").toString()

    fun saveNumber(mobile: String)=setPreferenceData(NUMBER,mobile)
    fun getNumber():String=sharedPreferences.getString(NUMBER,"").toString()

    fun saveWANumber(watsapp: String)=setPreferenceData(WANUMBER,watsapp)
    fun getWANumber():String=sharedPreferences.getString(WANUMBER,"").toString()

    fun saveVersions(value: String)=setPreferenceData(VERSION,value)
    fun getVersions():String=sharedPreferences.getString(VERSION,"").toString()

    fun saveEmail(email: String)=setPreferenceData(EMAIL,email)
    fun getEmail():String=sharedPreferences.getString(EMAIL,"").toString()



    fun removeAll() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()

    }




}


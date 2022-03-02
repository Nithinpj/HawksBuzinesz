package com.nithi.hawksbuziness.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManger @Inject constructor(val sharedPreferences: SharedPreferences) {
    companion object{
        const val URL="URL"
        const val USER_ID="USERID"
    }
    private fun setPreferenceData(key:String,value:String){
        val editor=sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun saveUrl(value:String){setPreferenceData(URL,value)}
    fun  getUrl():String=sharedPreferences.getString(URL,"").toString()

    fun saveId(value: String){setPreferenceData(USER_ID,value)}
    fun  getUserId():String=sharedPreferences.getString(USER_ID,"").toString()
}
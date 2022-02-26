package com.nithi.hawksbuziness.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManger @Inject constructor(val sharedPreferences: SharedPreferences) {
    companion object{
        const val URL="URL"
    }
    private fun setPreferenceData(key:String,value:String){
        val editor=sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun saveUrl(value:String){setPreferenceData(URL,value)}
    fun  getUrl():String=sharedPreferences.getString(URL,"").toString()
}
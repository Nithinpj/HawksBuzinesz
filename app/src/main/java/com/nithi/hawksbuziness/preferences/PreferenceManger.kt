package com.nithi.hawksbuziness.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManger @Inject constructor(val sharedPreferences: SharedPreferences) {
    companion object{
        const val NAME="NAME"
    }
    private fun setPreferenceData(key:String,value:String){
        val editor=sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun saveName(value:String){setPreferenceData(NAME,value)}
    fun  getName():String=sharedPreferences.getString(NAME,"").toString()
}
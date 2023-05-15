package com.example.digitalcalculator.util

import android.content.Context
import android.content.SharedPreferences
import com.example.digitalcalculator.BuildConfig


class AppPreference(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

    fun setStringPreference(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getStringPreference(key: String, def: String = ""): String {
        return sharedPreferences.getString(key, def)!!
    }

    fun setInputVoiceToggle(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getInputVoiceToggle(key: String, def: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, def)!!
    }

    fun setOutputVoiceToggle(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getOutputVoiceToggle(key: String, def: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, def)!!
    }



    companion object {
        private const val SHARED_PREF = BuildConfig.APPLICATION_ID

        const val APP_THEME = "app_theme"
        const val INPUT_VOICE="input_voice_toggle"
        const val OUTPUT_VOICE="output_voice_toggle"
        const val ACCENT_THEME = "app_accent_theme"


    }
}
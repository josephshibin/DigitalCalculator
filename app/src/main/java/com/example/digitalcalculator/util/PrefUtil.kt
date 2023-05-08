package com.example.digitalcalculator.util

import android.content.Context


object PrefUtil {
    /*** Basic calculator consts ***/
    private const val PRIMARY_TEXT_BC = "primary_text_bc"
    private const val SECONDARY_TEXT_BC = "secondary_text_bc"

    /*** Scientific calculator consts ***/
    private const val PRIMARY_TEXT_SC = "primary_text_sc"
    private const val SECONDARY_TEXT_SC = "secondary_text_sc"


    /*** BASIC calculator prefs ***/
    fun setPrimaryTextBC(context: Context, value: String) {
        context
            .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            .edit()
            .putString(PRIMARY_TEXT_BC, value)
            .apply()
    }

    fun getPrimaryTextBC(context: Context): String? {
        return context
            .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            .getString(PRIMARY_TEXT_BC, "")
    }

    fun setSecondaryTextBC(context: Context, value: String) {
        context
            .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            .edit()
            .putString(SECONDARY_TEXT_BC, value)
            .apply()
    }

    fun getSecondaryTextBC(context: Context): String? {
        return context
            .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            .getString(SECONDARY_TEXT_BC, "")
    }

    /*** Scientific calculator prefs ***/
    fun setPrimaryTextSC(context: Context, value: String) {
        context
            .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            .edit()
            .putString(PRIMARY_TEXT_SC, value)
            .apply()
    }

    fun getPrimaryTextSC(context: Context): String? {
        return context
            .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            .getString(PRIMARY_TEXT_SC, "")
    }

    fun setSecondaryTextSC(context: Context, value: String) {
        context
            .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            .edit()
            .putString(SECONDARY_TEXT_SC, value)
            .apply()
    }

    fun getSecondaryTextSC(context: Context): String? {
        return context
            .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            .getString(SECONDARY_TEXT_SC, "")
    }

}
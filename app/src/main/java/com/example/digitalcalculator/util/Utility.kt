package com.example.digitalcalculator.util

import com.example.digitalcalculator.R


//const val LAUNCHES_UNTIL_PROMPT = 5L
//const val DAYS_UNTIL_PROMPT = 3L

fun getAccentTheme(accentTheme: String): Int {
    return when (accentTheme) {
        AccentTheme.GREEN.name -> {
            R.style.AppTheme_Green
        }
        AccentTheme.PURPLE.name -> {
            R.style.AppTheme_Purple
        }
        AccentTheme.PINK.name -> {
            R.style.AppTheme_Pink
        }
        AccentTheme.RED.name -> {
            R.style.AppTheme_Red
        }
        AccentTheme.GREY.name -> {
            R.style.AppTheme_Grey
        }
        else -> {
            R.style.Theme_DigitalCalculator
        }
    }
}
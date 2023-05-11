package com.example.digitalcalculator.settings.viewmodel

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.digitalcalculator.util.AccentTheme
import com.example.digitalcalculator.util.AppPreference
import com.example.digitalcalculator.util.AppPreference.Companion.ACCENT_THEME
import com.example.digitalcalculator.util.AppPreference.Companion.APP_THEME
import com.example.digitalcalculator.util.AppTheme

class ViewModel(application: Application)  :AndroidViewModel(application) {
    private val appPreference = AppPreference(application)

    // Main theme
    private val _selectedTheme = MutableLiveData(getSelectedTheme())
    val selectedTheme: LiveData<AppTheme>
        get() = _selectedTheme


//        private val _selectedTheme = MutableLiveData<String>()
//    val selectedTheme: LiveData<String> =_selectedTheme

    private fun getSelectedTheme(): AppTheme {
        val themeName = appPreference.getStringPreference(APP_THEME, AppTheme.SYSTEM_DEFAULT.name)
        return try {
            AppTheme.valueOf(themeName)
        } catch (e: IllegalArgumentException) {
            AppTheme.SYSTEM_DEFAULT
        }
    }

    fun changeTheme(themeId: Int) {
        val theme = getAppThemeByOrdinal(themeId)
        appPreference.setStringPreference(APP_THEME, theme.name)
        val themeMode = when (theme) {
            AppTheme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            AppTheme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(themeMode)
        _selectedTheme.value = theme
    }
    fun getAppThemeByOrdinal(ordinal: Int): AppTheme {
        return AppTheme.values().find { it.ordinal == ordinal } ?: AppTheme.SYSTEM_DEFAULT
    }

    // accent theme
    private val _accentTheme = MutableLiveData(getAccentTheme())
    val accentTheme: LiveData<AccentTheme>
        get() = _accentTheme

    var selectedAccentTheme: AccentTheme = _accentTheme.value!!

    private fun getAccentTheme(): AccentTheme {

        val accentTheme = appPreference.getStringPreference(ACCENT_THEME, AccentTheme.BLUE.name)
        return try {
            AccentTheme.valueOf(accentTheme)
        } catch (e: IllegalArgumentException) {
            AccentTheme.BLUE
        }
    }
    fun setAccentTheme(accentTheme: AccentTheme) {
        appPreference.setStringPreference(ACCENT_THEME, accentTheme.name)
        _accentTheme.value = accentTheme
    }


}
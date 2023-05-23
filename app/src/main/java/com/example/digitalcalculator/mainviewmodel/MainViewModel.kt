package com.example.digitalcalculator.settings.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.digitalcalculator.domain.HistoryAdapterItem
import com.example.digitalcalculator.util.AppPreference


class MainViewModel(application: Application) : AndroidViewModel(application){
    private var appPreference=AppPreference(application)

    // MutableLiveData to keep track of the state of the InputVoice toggle button
    val toggleStateOfInputVoice = MutableLiveData<Boolean>()

    // MutableLiveData to keep track of the state of the OutputVoice toggle button
   val toggleStateOfOutputVoice = MutableLiveData<Boolean>()

  val isHistoryAvailable=MutableLiveData<Boolean>()


//    private val _selectedTheme = MutableLiveData<String>()
//    val selectedTheme: LiveData<String> =_selectedTheme

    fun setIsHistoryAvailable(isAvailable:Boolean){
        isHistoryAvailable.value=isAvailable
    }

    // Function to set the state of the InputVoice toggle button
    fun setToggleForInput(isToggleButtonOn: Boolean) {
        toggleStateOfInputVoice.value = isToggleButtonOn
    }

    // Function to set the state of the OutputVoice toggle button
    fun setToggleForOutput(isToggleButtonOn: Boolean) {
        toggleStateOfOutputVoice.value = isToggleButtonOn
    }



    // history list
    // val historyItems = mutableListOf<HistoryAdapterItem>()
    val lastThreeItems= mutableListOf<HistoryAdapterItem>()

}
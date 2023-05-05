package com.example.digitalcalculator.settings.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digitalcalculator.domain.HistoryAdapterItem


class MyViewModel(application: Application) : AndroidViewModel(application){

    // MutableLiveData to keep track of the state of the InputVoice toggle button
    val toggleStateOfInputVoice = MutableLiveData<Boolean>()

    // MutableLiveData to keep track of the state of the OutputVoice toggle button
   val toggleStateOfOutputVoice = MutableLiveData<Boolean>()


    private val _selectedTheme = MutableLiveData<String>()
    val selectedTheme: LiveData<String> =_selectedTheme
    fun setselectedTheme(currentItem:String) {
        _selectedTheme.value = currentItem
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
    val historyItems = mutableListOf<HistoryAdapterItem>()


}
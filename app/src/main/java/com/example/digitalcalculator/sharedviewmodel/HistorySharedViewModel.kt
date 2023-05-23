package com.example.digitalcalculator.sharedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digitalcalculator.domain.HistoryAdapterItem


class HistorySharedViewModel : ViewModel() {
    private val _currentHistoryDetails = MutableLiveData<HistoryAdapterItem>()
    val currentHistoryDetails: LiveData<HistoryAdapterItem> = _currentHistoryDetails
    fun setHistoryDetails(currentItem: HistoryAdapterItem) {
        _currentHistoryDetails.value = currentItem
    }
}
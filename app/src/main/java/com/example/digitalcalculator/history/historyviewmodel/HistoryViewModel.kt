package com.example.digitalcalculator.history.historyviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.digitalcalculator.cache.HistoryDatabase
import com.example.digitalcalculator.cache.model.HistoryEntity
import com.example.digitalcalculator.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application):AndroidViewModel(application){
    val readAllData:LiveData<List<HistoryEntity>>
    private val historyRepository: HistoryRepository

init {
    val historyDAO=HistoryDatabase.getInstance(application).historyDao()
    historyRepository= HistoryRepository(historyDAO)
    readAllData=historyRepository.allHistory

}
    fun insert(historyEntity: HistoryEntity)=viewModelScope.launch(Dispatchers.IO){
        historyRepository.insertHistoryToDatabase(historyEntity)
    }
    fun delete(historyEntity: HistoryEntity)=viewModelScope.launch(Dispatchers.IO){
        historyRepository.deleteHistory(historyEntity)
    }
}
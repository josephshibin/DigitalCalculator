package com.example.digitalcalculator.repository

import androidx.lifecycle.LiveData
import com.example.digitalcalculator.cache.model.HistoryEntity
import com.example.digitalcalculator.cache.dao.HistoryDao


class HistoryRepository (private val historyDao: HistoryDao) {

    val allHistory: LiveData<List<HistoryEntity>> = historyDao.getAllHistory()

    suspend fun deleteHistory(history: HistoryEntity) {
        historyDao.deleteHistoryByExpression(history)
    }
    suspend fun insertHistoryToDatabase(history: HistoryEntity) {
        historyDao.insertHistory(history)
    }
    suspend fun deleteAllHistory(){
        historyDao.clearHistory()
    }





}

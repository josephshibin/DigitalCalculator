package com.example.digitalcalculator.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.digitalcalculator.cache.model.HistoryEntity

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend  fun insertHistory(history: HistoryEntity)

    @Query("SELECT * FROM history ORDER BY  id ASC")
    fun getAllHistory(): LiveData<List<HistoryEntity>>

    @Query("DELETE FROM history")
    suspend fun clearHistory()

    @Delete
    suspend fun deleteHistoryByExpression(history:HistoryEntity)


}
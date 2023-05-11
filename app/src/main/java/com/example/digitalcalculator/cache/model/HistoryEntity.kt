package com.example.digitalcalculator.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digitalcalculator.domain.HistoryAdapterItem


@Entity(tableName = "history")
data class HistoryEntity(
   //  @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "expression") val expression: String,
    @ColumnInfo(name = "result") val result: String,
    // @ColumnInfo(name = "date") val date: Long

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)

fun HistoryEntity.toDomain(): HistoryAdapterItem {
    return HistoryAdapterItem(
        expression = expression,
        result = result,
       //  date = date
    )
}
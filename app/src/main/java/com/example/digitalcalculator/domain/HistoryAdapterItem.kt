package com.example.digitalcalculator.domain

import com.example.digitalcalculator.cache.model.HistoryEntity

data class HistoryAdapterItem(
    val expression: String,
    val result: String,
)
fun HistoryAdapterItem.toEntity(): HistoryEntity {
    return HistoryEntity(
        expression = expression,
        result = result
    )
}
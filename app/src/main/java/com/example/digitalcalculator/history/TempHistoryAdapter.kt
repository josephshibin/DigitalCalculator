package com.example.digitalcalculator.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcalculator.R
import com.example.digitalcalculator.domain.HistoryAdapterItem
import com.example.mobilecalculator.HistoryAdapter


class TempHistoryAdapter(private var lastThreeItems: MutableList<HistoryAdapterItem>) :
    RecyclerView.Adapter<TempHistoryAdapter.HistoryViewHolder>() {
    // private val lastThreeItems = mutableListOf<HistoryAdapterItem>()

    inner  class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val historyExpression: TextView = view.findViewById(R.id.expression_temp)
        val historyResult: TextView =view.findViewById(R.id.result_temp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.temp_history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val items = lastThreeItems[position]
        holder.historyExpression.text=items.expression
        holder.historyResult.text=items.result
    }

    override fun getItemCount() = lastThreeItems.size

//    fun addHistoryItem(item: String) {
//        historyItems.add(item)
//        notifyItemInserted(historyItems.size - 1)
//    }

    fun clearHistory() {
        lastThreeItems.clear()

        notifyDataSetChanged()
    }

    fun reverseHistory() {
        lastThreeItems.reverse()
        notifyDataSetChanged()
    }
}

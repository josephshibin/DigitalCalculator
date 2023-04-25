package com.example.mobilecalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcalculator.R
import com.example.digitalcalculator.domain.HistoryAdapterItem


class HistoryAdapter(private val historyItems: MutableList<HistoryAdapterItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

  inner  class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val historyExpression: TextView = view.findViewById(R.id.expression)
      val historyResult:TextView=view.findViewById(R.id.result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item_layout, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val items = historyItems[position]
        holder.historyExpression.text=items.expression
        holder.historyResult.text=items.result
    }

    override fun getItemCount() = historyItems.size

//    fun addHistoryItem(item: String) {
//        historyItems.add(item)
//        notifyItemInserted(historyItems.size - 1)
//    }

    fun clearHistory() {
        historyItems.clear()
        notifyDataSetChanged()
    }

    fun reverseHistory() {
        historyItems.reverse()
        notifyDataSetChanged()
    }
}

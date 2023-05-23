package com.example.digitalcalculator.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcalculator.R
import com.example.digitalcalculator.domain.HistoryAdapterItem


class HistoryAdapter(val from:Fragment,val onClickItem: (HistoryAdapterItem) -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var historyItems = emptyList<HistoryAdapterItem>()

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val historyExpression: TextView = view.findViewById(R.id.expression)
        val historyResult: TextView = view.findViewById(R.id.result)
        val linearLayout: LinearLayout = view.findViewById(R.id.linear_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item_layout, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val items = historyItems[position]
        holder.historyExpression.text = items.expression
        holder.historyResult.text = items.result
        holder.linearLayout.setOnClickListener {
            onClickItem(items)
            from.findNavController().navigate(R.id.action_historyFragment_to_twoInOneCalculator)

        }
    }

    override fun getItemCount() = historyItems.size

//    fun addHistoryItem(item: String) {
//        historyItems.add(item)
//        notifyItemInserted(historyItems.size - 1)
//    }

    fun setHistory(historyAdapterItem: List<HistoryAdapterItem>) {
        this.historyItems = historyAdapterItem
        notifyDataSetChanged()
    }

    fun getHistory(position: Int): HistoryAdapterItem {
        return historyItems[position]
    }


//    fun clearHistory() {
//        historyItems.clear()
//        notifyDataSetChanged()
//    }
//
//    fun reverseHistory() {
//        historyItems.reverse()
//        notifyDataSetChanged()
//    }
}

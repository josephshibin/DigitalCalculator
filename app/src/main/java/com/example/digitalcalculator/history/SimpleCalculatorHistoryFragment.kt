package com.example.mobilecalculator.history

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcalculator.R
import com.example.digitalcalculator.cache.model.toDomain
import com.example.digitalcalculator.databinding.FragmentHistoryBinding
import com.example.digitalcalculator.domain.HistoryAdapterItem
import com.example.digitalcalculator.domain.toEntity
import com.example.digitalcalculator.history.adapter.HistoryAdapter
import com.example.digitalcalculator.history.historyviewmodel.HistoryViewModel
import com.example.digitalcalculator.settings.viewmodel.MainViewModel
import com.example.digitalcalculator.sharedviewmodel.HistorySharedViewModel
import com.example.digitalcalculator.util.AppPreference
import com.example.digitalcalculator.util.AppPreference.Companion.HISTORY_AVAILABLE


class SimpleCalculatorHistoryFragment() : Fragment() {
    private lateinit var binding: FragmentHistoryBinding


    private lateinit var historyViewModel: HistoryViewModel

    // initializing shared view model
    private val sharedViewModel: HistorySharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        val view = binding.root


        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = HistoryAdapter(this) { historyAdapterItem -> onClickItem(historyAdapterItem) }
        recyclerView.adapter = adapter
        historyViewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
        historyViewModel.readAllData.observe(viewLifecycleOwner) { it ->
            val history = it.map { it.toDomain() }

            adapter.setHistory(history)

            (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(history.size - 1)
        }
        // delete by sliding
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                //  historyViewModel.delete(adapter.getHistory(viewHolder.adapterPosition).toEntity())
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

        }).attachToRecyclerView(recyclerView)


        setHasOptionsMenu(true)
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(com.example.digitalcalculator.R.menu.delete, menu)
    }


    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            com.example.digitalcalculator.R.id.delete -> {

                showDialogMessage()
            }

        }

        return true
    }


    private fun showDialogMessage() {

        val dialogMessage = AlertDialog.Builder(requireContext())
        dialogMessage.setTitle("Delete All History")
        dialogMessage.setMessage(
            "If click Yes all History will delete" +
                    ", if you want to delete a specific History, please swipe right."
        )
        dialogMessage.setNegativeButton("No") { dialog, _ ->

            dialog.cancel()

        }
        dialogMessage.setPositiveButton("Yes") { _, _ ->

            historyViewModel.deleteAllHistory()
            //    view?.findNavController()?.navigate(R.id.action_historyFragment_to_twoInOneCalculator)
        }
        dialogMessage.create().show()
    }

    private fun onClickItem(item: HistoryAdapterItem) {
        sharedViewModel.setHistoryDetails(item)

    }

}
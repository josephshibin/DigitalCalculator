package com.example.mobilecalculator.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalcalculator.cache.model.toDomain
import com.example.digitalcalculator.databinding.FragmentHistoryBinding
import com.example.digitalcalculator.history.adapter.HistoryAdapter
import com.example.digitalcalculator.history.historyviewmodel.HistoryViewModel


class SimpleCalculatorHistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding



    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(context)

       historyViewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
     historyViewModel.readAllData.observe(viewLifecycleOwner){it->
        val   history= it.map { it.toDomain() }
         val adapter = HistoryAdapter()
         adapter.setHistory(history)
         recyclerView.adapter = adapter
         (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(history.size - 1)
        }



         // setHasOptionsMenu(true)
        return view
    }
//    @Deprecated("Deprecated in Java")
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.settingsmenu, menu)
//    }
//
//    @Deprecated("Deprecated in Java")
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//
////            R.id.delete_history -> {
////                myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
////                val adapter = HistoryAdapter(myViewModel.historyItems)
////                adapter.clearHistory()
////
////            }
//
//
//
//        }
//
//        return true
//    }



}
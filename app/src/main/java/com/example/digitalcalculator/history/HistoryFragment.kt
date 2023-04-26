package com.example.mobilecalculator.history

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.FragmentHistoryBinding
import com.example.digitalcalculator.viewmodel.MyViewModel
import com.example.mobilecalculator.HistoryAdapter



class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding


    private lateinit var myViewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(context)

        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        val adapter = HistoryAdapter(myViewModel.historyItems)
        adapter.notifyItemInserted(myViewModel.historyItems.size+1)
        // Log.i("list", myViewModel.historyItems.toString())
       // adapter.reverseHistory()
        recyclerView.adapter = adapter

         // setHasOptionsMenu(true)
        return view
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.deletemenu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.delete_history -> {
                myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
                val adapter = HistoryAdapter(myViewModel.historyItems)
                adapter.clearHistory()

            }



        }

        return true
    }



}
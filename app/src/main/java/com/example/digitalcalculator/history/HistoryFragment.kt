package com.example.mobilecalculator.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
        Log.i("list", myViewModel.historyItems.toString())
       // adapter.reverseHistory()
        recyclerView.adapter = adapter


        return view
    }


}
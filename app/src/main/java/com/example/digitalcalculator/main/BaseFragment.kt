package com.example.digitalcalculator.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.digitalcalculator.R

import com.example.digitalcalculator.databinding.FragmentBaseBinding
import com.example.digitalcalculator.main.simplecalculator.SimpleCalculatorBaseFragment



class BaseFragment : Fragment() {
    private lateinit var binding: FragmentBaseBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = FragmentBaseBinding.inflate(layoutInflater, container, false)
   binding.btnSimpleCalculator.setOnClickListener {

       it.findNavController().navigate(R.id.action_baseFragment_to_simpleCalculatorFragment)
       // Add the current fragment to the backstack
       parentFragmentManager.beginTransaction().addToBackStack(null).commit()
   }
        binding.btnScientificCalculator.setOnClickListener {
            it.findNavController().navigate(R.id.action_baseFragment_to_scientificFragment)
        }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settingsmenu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

                R.id.settings -> {
                    view?.findNavController()?.navigate(R.id.action_baseFragment_to_settings)
                }

        }

        return true
    }

}
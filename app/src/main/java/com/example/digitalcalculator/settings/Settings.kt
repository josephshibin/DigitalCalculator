package com.example.digitalcalculator.settings

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.FragmentSettingsBinding
import com.example.digitalcalculator.viewmodel.MyViewModel


class Settings : Fragment() {

    private lateinit var viewModel: MyViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var vibrator: Vibrator
    private lateinit var binding: FragmentSettingsBinding
    var toggele=false



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentSettingsBinding.inflate(inflater, container, false)


        // Load the saved dark mode preference
        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val isDarkModeEnabled = sharedPreferences.getBoolean("isDarkModeEnabled", false)

       // binding.darkModeSwitch.isChecked = isDarkModeEnabled

        // Saving the dark mode preference when the user toggles the switch
//        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
//            sharedPreferences.edit().putBoolean("isDarkModeEnabled", isChecked).apply()
//            requireActivity().recreate()
//        }
        binding.theme.setOnClickListener {
            showOptionsDialog()
        }

        return binding.root
    }
    private fun showOptionsDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialogbox, null)
        builder.setView(view)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        val radioButton1 = view.findViewById<RadioButton>(R.id.system_default)
        val radioButton2 = view.findViewById<RadioButton>(R.id.light)
        val radioButton3 = view.findViewById<RadioButton>(R.id.dark)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.system_default -> {
                    //Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    toggele=true
                }
                R.id.light -> {
                   // Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    toggele=false

                }
                R.id.dark -> {
                   // Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().putBoolean("isDarkModeEnabled", true).apply()






                    toggele=false

                }
            }
        }
        builder.setPositiveButton("OK") { dialog, _ ->
            if(toggele) {
                dialog.dismiss()
            }else {
                dialog.dismiss()

//                requireActivity().recreate()
            }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        // Initialize the SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        // Set the switch's state to the value stored in SharedPreferences, defaulting to false
        binding.switchOutputVoice.isChecked = sharedPreferences.getBoolean("output_voice_enabled", false)
        binding.switchInputVoice.isChecked = sharedPreferences.getBoolean("input_voice_enabled", false)

        // Set the listener for the Output switch
        binding.switchOutputVoice.setOnCheckedChangeListener { _, isChecked ->
            // Save the switch state to SharedPreferences
            sharedPreferences.edit().putBoolean("output_voice_enabled", isChecked).apply()

            // Update the ViewModel's toggle state
            viewModel.setToggleForOutput(isChecked)

            // Vibrate for 50 milliseconds
            val vibrationEffect = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)
        }

        // Set the listener for the Input switch
        binding.switchInputVoice.setOnCheckedChangeListener { _, isChecked ->
            // Save the switch state to SharedPreferences
            sharedPreferences.edit().putBoolean("input_voice_enabled", isChecked).apply()

            // Update the ViewModel's toggle state
            viewModel.setToggleForInput(isChecked)
        }
    }
}

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
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.FragmentSettingsBinding
import com.example.digitalcalculator.settings.viewmodel.MyViewModel


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

        binding.theme.setOnClickListener {
            showOptionsDialog()
        }
        binding.accent.setOnClickListener {
            showColorDialog()
        }

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)


        return binding.root
    }

    private fun showColorDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.color_dialog1, null)
        builder.setView(view)
         builder.create().show()
        val colorDefault=view.findViewById<FrameLayout>(R.id.color_default)
        val defaultCheck=view.findViewById<ImageView>(R.id.default_check)
        val green=view.findViewById<FrameLayout>(R.id.color_green)
        val greenCheck=view.findViewById<ImageView>(R.id.green_check)
        val purple=view.findViewById<FrameLayout>(R.id.color_purple)
        val purpleCheck=view.findViewById<ImageView>(R.id.purple_check)
        val pink=view.findViewById<FrameLayout>(R.id.color_pink)
        val pinkCheck=view.findViewById<ImageView>(R.id.pink_check)
        val red=view.findViewById<FrameLayout>(R.id.color_red)
        val redCheck = view.findViewById<ImageView>(R.id.red_check)
        val grey=view.findViewById<FrameLayout>(R.id.color_grey)
        val greyCheck=view.findViewById<ImageView>(R.id.grey_check)


   //    colorDefault.setOnLongClickListener {  }

    }

    private fun showOptionsDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialogbox, null)
        builder.setView(view)
        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val theme = sharedPreferences.getInt("theme", -1)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        val radioButton1 = view.findViewById<RadioButton>(R.id.system_default)
        val radioButton2 = view.findViewById<RadioButton>(R.id.light)
        val radioButton3 = view.findViewById<RadioButton>(R.id.dark)

        if(theme==1){
            radioButton2.isChecked=true
        }else if(theme==2){
            radioButton3.isChecked=true
        }else{
            radioButton1.isChecked=true
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.system_default -> {
                    //Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().putInt("theme", 0).apply()
                    sharedPreferences.edit().putString("selectedTheme", "System Default").apply()
                    toggele=true
                }
                R.id.light -> {
                   // Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().putInt("theme", 1).apply()
                    sharedPreferences.edit().putString("selectedTheme", "Light").apply()
                    toggele=false

                }
                R.id.dark -> {
                   // Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().putInt("theme", 2).apply()
                    sharedPreferences.edit().putString("selectedTheme", "Dark").apply()
                    toggele=false

                }
            }
        }
        builder.setPositiveButton("OK") { dialog, _ ->

            if(toggele) {
                val selectedTheme = sharedPreferences.getString("selectedTheme", "")
                if (selectedTheme != null) {
                    viewModel.setselectedTheme(selectedTheme)
                }
                dialog.dismiss()
                requireActivity().recreate()
            }else {
                val selectedTheme = sharedPreferences.getString("selectedTheme", "")
                if (selectedTheme != null) {
                    viewModel.setselectedTheme(selectedTheme)
                }
                dialog.dismiss()
                requireActivity().recreate()

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

        viewModel.selectedTheme.observe(viewLifecycleOwner){
            binding.selectedTheme.text=it
        }

    }

}

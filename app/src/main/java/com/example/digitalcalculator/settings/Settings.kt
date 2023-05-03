package com.example.digitalcalculator.settings

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.ColorDialog1Binding
import com.example.digitalcalculator.databinding.FragmentSettingsBinding
import com.example.digitalcalculator.settings.viewmodel.MyViewModel
import com.example.digitalcalculator.settings.viewmodel.ViewModel
import com.example.digitalcalculator.util.AccentTheme
import com.example.digitalcalculator.util.AppTheme
import com.example.digitalcalculator.util.visible


class Settings : Fragment() {

    private lateinit var viewModel: MyViewModel

    //private lateinit var settingsViewModel: ViewModel
    private val settingsViewModel by viewModels<ViewModel>()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var vibrator: Vibrator
    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        // Initialize the SharedPreferences
        sharedPreferences =
            requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        viewModel.selectedTheme.observe(viewLifecycleOwner) {
            binding.selectedTheme.text = it
        }
        setUpView()
        setUpObservables()
        return binding.root
    }



    private fun showOptionsDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialogbox, null)
        builder.setView(view)
        var selectedThemeChoice = settingsViewModel.selectedTheme.value!!

        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        val radioButton1 = view.findViewById<RadioButton>(R.id.system_default)
        val radioButton2 = view.findViewById<RadioButton>(R.id.light)
        val radioButton3 = view.findViewById<RadioButton>(R.id.dark)

        if (selectedThemeChoice == AppTheme.LIGHT) {
            radioButton2.isChecked = true
        } else if (selectedThemeChoice == AppTheme.DARK) {
            radioButton3.isChecked = true
        } else {
            radioButton1.isChecked = true
        }

        var selectedTheme=0

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.system_default -> {

                    // settingsViewModel.changeTheme(AppTheme.SYSTEM_DEFAULT.ordinal)
                    selectedTheme=AppTheme.SYSTEM_DEFAULT.ordinal

                }
                R.id.light -> {

                    //settingsViewModel.changeTheme(AppTheme.LIGHT.ordinal)
                    selectedTheme=AppTheme.LIGHT.ordinal

                }
                R.id.dark -> {

                    //settingsViewModel.changeTheme(AppTheme.DARK.ordinal)
                    selectedTheme=AppTheme.DARK.ordinal

                }
            }
        }
        builder.setPositiveButton("OK") { dialog, _ ->
            settingsViewModel.changeTheme(selectedTheme)
            dialog.dismiss()


        }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()

    }

    private fun setUpObservables() {
       settingsViewModel.selectedTheme.observe(viewLifecycleOwner) {
           val theme=it.name.lowercase().capitalize()
            binding.selectedTheme.text = theme

        }
        // settingsViewModel=ViewModelProvider(requireActivity()).get(ViewModel::class.java)
        settingsViewModel.accentTheme.observe(viewLifecycleOwner) {
            val accentTheme = it.name.lowercase().capitalize()
            binding.accentColor.text = accentTheme
        }
    }

    private fun setUpView() {


        binding.theme.setOnClickListener {

            showOptionsDialog()
        }

        val colorDialog = ColorDialog1Binding.inflate(layoutInflater, null, false)
        colorDialog.colorDefault.setOnClickListener {
            checkAccentTheme(AccentTheme.BLUE, colorDialog)
        }
        colorDialog.colorGreen.setOnClickListener {
            checkAccentTheme(AccentTheme.GREEN, colorDialog)
        }
        colorDialog.colorPurple.setOnClickListener {
            checkAccentTheme(AccentTheme.PURPLE, colorDialog)
        }
        colorDialog.colorPink.setOnClickListener {
            checkAccentTheme(AccentTheme.PINK, colorDialog)
        }
        colorDialog.colorRed.setOnClickListener {
            checkAccentTheme(AccentTheme.RED, colorDialog)
        }
        colorDialog.colorGrey.setOnClickListener {
            checkAccentTheme(AccentTheme.GREY, colorDialog)
        }


        binding.accent.setOnClickListener {

                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))

            if (colorDialog.root.parent != null) {
                ((colorDialog.root.parent) as ViewGroup).removeView(colorDialog.root)
            }
            checkAccentTheme(settingsViewModel.selectedAccentTheme, colorDialog)
            val builder = AlertDialog.Builder(requireContext())
                .setView(colorDialog.root)
                .setTitle("choose the theme")
                .setPositiveButton("ok") { dialog, _ ->
                    settingsViewModel.setAccentTheme(settingsViewModel.selectedAccentTheme)
                    dialog.dismiss()
                    requireActivity().recreate()
                }
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.dismiss()
                }.show()

        }
    }

    private fun checkAccentTheme(accentTheme: AccentTheme, colorDialog: ColorDialog1Binding) {
        colorDialog.defaultCheck.visible(false)
        colorDialog.greenCheck.visible(false)
        colorDialog.purpleCheck.visible(false)
        colorDialog.pinkCheck.visible(false)
        colorDialog.redCheck.visible(false)
        colorDialog.greyCheck.visible(false)
        when (accentTheme) {
            AccentTheme.BLUE -> {
                colorDialog.defaultCheck.visible(true)
            }
            AccentTheme.GREEN -> {
                colorDialog.greenCheck.visible(true)
            }
            AccentTheme.PURPLE -> {
                colorDialog.purpleCheck.visible(true)
            }
            AccentTheme.PINK -> {
                colorDialog.pinkCheck.visible(true)
            }
            AccentTheme.RED -> {
                colorDialog.redCheck.visible(true)
            }
            AccentTheme.GREY -> {
                colorDialog.greyCheck.visible(true)
            }
        }
        settingsViewModel.selectedAccentTheme = accentTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator


        // Set the switch's state to the value stored in SharedPreferences, defaulting to false
        binding.switchOutputVoice.isChecked =
            sharedPreferences.getBoolean("output_voice_enabled", false)
        binding.switchInputVoice.isChecked =
            sharedPreferences.getBoolean("input_voice_enabled", false)

        // Set the listener for the Output switch
        binding.switchOutputVoice.setOnCheckedChangeListener { _, isChecked ->
            // Save the switch state to SharedPreferences
            sharedPreferences.edit().putBoolean("output_voice_enabled", isChecked).apply()

            // Update the ViewModel's toggle state
            viewModel.setToggleForOutput(isChecked)

            // Vibrate for 50 milliseconds
//            val vibrationEffect =
//                VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
//            vibrator.vibrate(vibrationEffect)
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

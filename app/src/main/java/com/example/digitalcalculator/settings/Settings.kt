package com.example.digitalcalculator.settings

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.StatsLog.logEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.ColorDialog1Binding
import com.example.digitalcalculator.databinding.FragmentSettingsBinding
import com.example.digitalcalculator.settings.viewmodel.MyViewModel
import com.example.digitalcalculator.util.AccentTheme
import com.example.digitalcalculator.util.AppPreference
import com.example.digitalcalculator.util.getAccentTheme
import com.example.digitalcalculator.util.visible
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class Settings : Fragment() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        // Get a reference to the activity
//        val activity = requireActivity()
//        // Call the setTheme() method on the activity
//
//        val appPreference = AppPreference(activity)
//        val accentTheme =
//            appPreference.getStringPreference(AppPreference.ACCENT_THEME, AccentTheme.BLUE.name)
//       activity.setTheme(getAccentTheme(accentTheme))
//        super.onCreate(savedInstanceState)
//
//    }

    private lateinit var viewModel: MyViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var vibrator: Vibrator
    private lateinit var binding: FragmentSettingsBinding
    var toggele = false


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

        // Initialize the SharedPreferences
        sharedPreferences =
            requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        viewModel.selectedTheme.observe(viewLifecycleOwner) {
            binding.selectedTheme.text = it
        }
        return binding.root
    }

    private fun showColorDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.color_dialog1, null)
        builder.setView(view)
        builder.create().show()
        val colorDefault = view.findViewById<FrameLayout>(R.id.color_default)
        val defaultCheck = view.findViewById<ImageView>(R.id.default_check)
        val green = view.findViewById<FrameLayout>(R.id.color_green)
        val greenCheck = view.findViewById<ImageView>(R.id.green_check)
        val purple = view.findViewById<FrameLayout>(R.id.color_purple)
        val purpleCheck = view.findViewById<ImageView>(R.id.purple_check)
        val pink = view.findViewById<FrameLayout>(R.id.color_pink)
        val pinkCheck = view.findViewById<ImageView>(R.id.pink_check)
        val red = view.findViewById<FrameLayout>(R.id.color_red)
        val redCheck = view.findViewById<ImageView>(R.id.red_check)
        val grey = view.findViewById<FrameLayout>(R.id.color_grey)
        val greyCheck = view.findViewById<ImageView>(R.id.grey_check)


        //    colorDefault.setOnLongClickListener {  }

    }

    private fun showOptionsDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialogbox, null)
        builder.setView(view)
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val theme = sharedPreferences.getInt("theme", -1)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        val radioButton1 = view.findViewById<RadioButton>(R.id.system_default)
        val radioButton2 = view.findViewById<RadioButton>(R.id.light)
        val radioButton3 = view.findViewById<RadioButton>(R.id.dark)

        if (theme == 1) {
            radioButton2.isChecked = true
        } else if (theme == 2) {
            radioButton3.isChecked = true
        } else {
            radioButton1.isChecked = true
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.system_default -> {
                    //Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().putInt("theme", 0).apply()
                    sharedPreferences.edit().putString("selectedTheme", "System Default").apply()

                }
                R.id.light -> {
                    // Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().putInt("theme", 1).apply()
                    sharedPreferences.edit().putString("selectedTheme", "Light").apply()


                }
                R.id.dark -> {
                    // Toast.makeText(requireContext(),"hii",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().putInt("theme", 2).apply()
                    sharedPreferences.edit().putString("selectedTheme", "Dark").apply()


                }
            }
        }
        builder.setPositiveButton("OK") { dialog, _ ->

            val selectedTheme = sharedPreferences.getString("selectedTheme", "")
            if (selectedTheme != null) {
                viewModel.setselectedTheme(selectedTheme)
            }
            dialog.dismiss()
            requireActivity().recreate()

        }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()


    }

//    private fun setUpView() {
//
//        val colorDialog = ColorDialog1Binding.inflate(layoutInflater, null, false)
//        colorDialog.colorDefault.setOnClickListener {
//            checkAccentTheme(AccentTheme.BLUE, colorDialog)
//        }
//        colorDialog.colorGreen.setOnClickListener {
//            checkAccentTheme(AccentTheme.GREEN, colorDialog)
//        }
//        colorDialog.colorPurple.setOnClickListener {
//            checkAccentTheme(AccentTheme.PURPLE, colorDialog)
//        }
//        colorDialog.colorPink.setOnClickListener {
//            checkAccentTheme(AccentTheme.PINK, colorDialog)
//        }
//        colorDialog.colorRed.setOnClickListener {
//            checkAccentTheme(AccentTheme.RED, colorDialog)
//        }
//        colorDialog.colorGrey.setOnClickListener {
//            checkAccentTheme(AccentTheme.GREY, colorDialog)
//        }
//        binding.accent.setOnClickListener {
//            if (colorDialog.root.parent != null) {
//                ((colorDialog.root.parent) as ViewGroup).removeView(colorDialog.root)
//            }
//            checkAccentTheme(viewModel.selectedAccentTheme, colorDialog)
//           val dialog = MaterialAlertDialogBuilder(this)
//                .setView(colorDialog.root)
//                .setTitle("choose the theme")
//                .setPositiveButton("ok") { dialog, _ ->
//                    viewModel.setAccentTheme(viewModel.selectedAccentTheme)
//                    dialog.dismiss()
////                    val intent = arrayOfNulls<Intent>(2)
////                    intent[1] = Intent(this, Settings::class.java)
////                    intent[0] = Intent(
////                        this,
////                        MainActivity::class.java
////                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
////                    startActivities(intent)
////                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
////                    finish()
//                }
//                .setNegativeButton("cancel") { dialog, _ ->
//                    dialog.dismiss()
//                }.show()
//        }
//    }
//    private fun checkAccentTheme(accentTheme: AccentTheme, colorDialog: ColorDialog1Binding) {
//        colorDialog.defaultCheck.visible(false)
//        colorDialog.greenCheck.visible(false)
//        colorDialog.purpleCheck.visible(false)
//        colorDialog.pinkCheck.visible(false)
//        colorDialog.redCheck.visible(false)
//        colorDialog.greyCheck.visible(false)
//        when (accentTheme) {
//            AccentTheme.BLUE -> {
//                colorDialog.defaultCheck.visible(true)
//            }
//            AccentTheme.GREEN -> {
//                colorDialog.greenCheck.visible(true)
//            }
//            AccentTheme.PURPLE -> {
//                colorDialog.purpleCheck.visible(true)
//            }
//            AccentTheme.PINK -> {
//                colorDialog.pinkCheck.visible(true)
//            }
//            AccentTheme.RED -> {
//                colorDialog.redCheck.visible(true)
//            }
//            AccentTheme.GREY -> {
//                colorDialog.greyCheck.visible(true)
//            }
//        }
//        viewModel.selectedAccentTheme = accentTheme
//    }

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
            val vibrationEffect =
                VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
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

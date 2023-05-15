package com.example.digitalcalculator.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.FragmentTwoInOneBinding
import com.example.digitalcalculator.domain.HistoryAdapterItem
import com.example.digitalcalculator.domain.toEntity
import com.example.digitalcalculator.gestures.SwipeGestureListener
import com.example.digitalcalculator.history.historyviewmodel.HistoryViewModel
import com.example.digitalcalculator.main.ScreenUtils.getScreenHeight
import com.example.digitalcalculator.settings.viewmodel.MainViewModel

import com.example.digitalcalculator.util.ButtonUtil
import com.example.digitalcalculator.util.CalculationUtil
import java.util.Locale
import kotlin.math.sqrt
import kotlin.properties.Delegates


class TwoInOneCalculator : Fragment() {
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentTwoInOneBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var isScientificCalculator = false
    private var isSecondEnable = true
    private var isDegreeEnable = true
    //private var toggleStateOfInputVoice =false

    // swipe gesture
    private lateinit var gestureDetector: GestureDetector
    private var isBackClearEnable = true

    // voice input
    private val SPEECH_REQUEST_CODE = 0

    // text to speech
    private lateinit var textToSpeech: TextToSpeech


    companion object {
        var addedOperator = false
        var addedTrigno = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentTwoInOneBinding.inflate(layoutInflater, container, false)
        binding.mainLayout.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
        // Initialize the SharedPreferences
        sharedPreferences =
            requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)


        setScreen(0.40)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //text to speech
        textToSpeech = TextToSpeech(requireContext(), TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.US
            }
        })


        gestureDetector = GestureDetector(requireContext(), SwipeGestureListener(
            onSwipeUp = {
                // handle swipe up
                Toast.makeText(requireContext(), "Swipe Up", Toast.LENGTH_SHORT).show()
                onSwipeUpToVoiceInput()
            },
            onSwipeDown = {
                // handle swipe down
                Toast.makeText(requireContext(), "Swipe Down", Toast.LENGTH_SHORT).show()
            },
            onSwipeLeft = {
                // handle swipe left
                Toast.makeText(requireContext(), "Swipe Left", Toast.LENGTH_SHORT).show()
            },
            onSwipeRight = {
                // handle swipe right
                Toast.makeText(requireContext(), "Swipe Right", Toast.LENGTH_SHORT).show()
            }
        ))

        binding.btnChangeCalculator1.setOnClickListener {
            if (isScientificCalculator) {
                isScientificCalculator = false
                setScreen(0.40)
                setVisibilityScientific()

            } else {
                isScientificCalculator = true
                setScreen(0.30)
                setVisibilityScientific()

            }
        }
        binding.btnChangeCalculator2.setOnClickListener {
            if (isScientificCalculator) {
                isScientificCalculator = false
                setScreen(0.40)
                setVisibilityScientific()

            } else {
                isScientificCalculator = true
                setScreen(0.30)
                setVisibilityScientific()

            }
        }


        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnZero,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnOne,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnTwo,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnThree,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnFour,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnFive,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnSix,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnSeven,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnEight,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnNine,
            binding.tvInputCalculation, binding.btnBackClear, binding.tvEqualCalculation,
            1
        )

        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnSin,
            binding.tvInputCalculation, binding.btnBackClear,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnCos,
            binding.tvInputCalculation, binding.btnBackClear,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnTan,
            binding.tvInputCalculation, binding.btnBackClear,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnLog,
            binding.tvInputCalculation, binding.btnBackClear,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnNaturalLog,
            binding.tvInputCalculation, binding.btnBackClear,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnParenthesisStart,
            binding.tvInputCalculation, binding.btnBackClear,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnParenthesisClose,
            binding.tvInputCalculation, binding.btnBackClear,
            1
        )

        ButtonUtil.addOperatorValueToText(
            requireContext(),
            binding.btnAddition,
            binding.tvInputCalculation, binding.btnBackClear,
            "+",
            1
        )
        ButtonUtil.addOperatorValueToText(
            requireContext(),
            binding.btnSubtraction,
            binding.tvInputCalculation, binding.btnBackClear,
            "-",
            1
        )
        ButtonUtil.addOperatorValueToText(
            requireContext(),
            binding.btnMultiplication,
            binding.tvInputCalculation, binding.btnBackClear,
            "*",
            1
        )
        ButtonUtil.addOperatorValueToText(
            requireContext(),
            binding.btnDivision,
            binding.tvInputCalculation, binding.btnBackClear,
            "/",
            1
        )
        binding.btnDot.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())
            if (!binding.tvInputCalculation.text.contains(".")) binding.tvInputCalculation.text =
                binding.tvInputCalculation.text.toString() + "."
        }


        binding.btnPi.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())
            if (binding.tvInputCalculation.text.isEmpty() && binding.tvEqualCalculation.text.isEmpty()) {
                binding.tvEqualCalculation.text =
                    binding.tvEqualCalculation.text.toString() + "3.142"
                binding.tvInputCalculation.text = binding.btnPi.text.toString()
            }
            if(addedOperator){
                binding.tvInputCalculation.text = binding.tvInputCalculation.text.toString() + "3.142"
            }
        }

        binding.btnPower.setOnClickListener {
            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(
                    requireContext()
                )
                else {
                    //TODO
                    val input = binding.tvInputCalculation.text.toString()
                    binding.tvInputCalculation.text = "$input^("

                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())

            }
        }

        binding.btnFact.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(
                    requireContext()
                )
                else {
                    val input = binding.tvInputCalculation.text.toString()
                    var factorial = 1.0
                    for (i in 1..input.toLong()) {
                        factorial *= i
                    }
                    binding.tvEqualCalculation.text =
                        CalculationUtil.trimResult(factorial.toString())
                    binding.tvInputCalculation.text = "$input!"
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }


        binding.btnMultiplicativeInverse.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(
                    requireContext()
                )
                else {
                    val input = binding.tvInputCalculation.text.toString()
                    val result = (1 / input.toFloat()).toString()
                    binding.tvEqualCalculation.text = CalculationUtil.trimResult(result)
                    binding.tvInputCalculation.text = "1/$input"
                    textToSpeech(binding.tvInputCalculation.text.toString())
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }

        binding.btnSquareRoot.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(
                    requireContext()
                )
                else {
                    val input = binding.tvInputCalculation.text.toString()
                    val result = (sqrt(input.toFloat())).toString()
                    binding.tvEqualCalculation.text = CalculationUtil.trimResult(result)
                    binding.tvInputCalculation.text = "√$input"
                    textToSpeech(binding.tvEqualCalculation.text.toString())
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }

        binding.btnAllClear.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            binding.tvInputCalculation.text = ""
            binding.tvEqualCalculation.text = ""
            addedOperator = false
        }

        binding.btnBackClear.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            if (binding.tvInputCalculation.text.contains("+") ||
                binding.tvInputCalculation.text.contains("-") ||
                binding.tvInputCalculation.text.contains("*") ||
                binding.tvInputCalculation.text.contains("/")
            ) addedOperator = false

            if (binding.tvInputCalculation.text.isNotEmpty()) binding.tvInputCalculation.text =
                binding.tvInputCalculation.text.subSequence(
                    0,
                    binding.tvInputCalculation.length() - 1
                )
        }

        binding.btnPercentage.setOnClickListener {
            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(
                    requireContext()
                )
                else {
                    val input = binding.tvInputCalculation.text.toString()
                    val result = (input.toFloat() / 100).toString()
                    binding.tvInputCalculation.text = CalculationUtil.trimResult(result)
                    binding.tvEqualCalculation.text = "$input%"
                    textToSpeech(binding.tvInputCalculation.text.toString())
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }

        binding.btnEqual.setOnClickListener {
            equalAction()

        }

        binding.btnSecond.setOnClickListener {
            changingSecond()
        }
        binding.btnDegree.setOnClickListener {
            changingDegree()
        }


    }

    private fun equalAction() {
        ButtonUtil.vibratePhone(requireContext())
        try {
            if (binding.tvInputCalculation.text.isNotEmpty()) {
                val input = binding.tvInputCalculation.text.toString()
                val result = CalculationUtil.evaluate(input).toString()
              val finalResult  = CalculationUtil.trimResult(result)
                binding.tvInputCalculation.text = input
                binding.tvEqualCalculation.text = "= $finalResult"
                addedOperator = false
                val answer = binding.tvEqualCalculation.text.toString()
                val expression = binding.tvInputCalculation.text.toString()
                val currentItem = HistoryAdapterItem(expression, answer)
                addToTheHistory(currentItem)
                // below line make the back clear button disable after getting the result
                //  it will be enabled wen other button is clicked
                binding.btnBackClear.isEnabled = false
                textToSpeech(answer)
            }
        } catch (e: Exception) {
            ButtonUtil.invalidInputToast(requireContext())
            textToSpeech("Invalid Input")
        }


    }


    private fun setScreen(percentage: Double) {
        binding.topLayout.requestLayout()
        binding.topLayout.layoutParams.height = (activity.getScreenHeight() * percentage).toInt()
    }

    private fun setVisibilityScientific() {
        if (isScientificCalculator) {
            binding.btnSecond.visibility = View.VISIBLE
            binding.btnDegree.visibility = View.VISIBLE
            binding.btnSin.visibility = View.VISIBLE
            binding.btnCos.visibility = View.VISIBLE
            binding.btnTan.visibility = View.VISIBLE
            binding.btnPower.visibility = View.VISIBLE
            binding.btnLog.visibility = View.VISIBLE
            binding.btnNaturalLog.visibility = View.VISIBLE
            binding.btnParenthesisStart.visibility = View.VISIBLE
            binding.btnParenthesisClose.visibility = View.VISIBLE
            binding.btnSquareRoot.visibility = View.VISIBLE
            binding.btnFact.visibility = View.VISIBLE
            binding.btnMultiplicativeInverse.visibility = View.VISIBLE
            binding.btnPi.visibility = View.VISIBLE
            binding.btnChangeCalculatorLayout.visibility = View.VISIBLE
            binding.btnChangeCalculator1.visibility = View.VISIBLE
            binding.btnChangeCalculator2.visibility = View.GONE
            binding.btnExponent1.visibility = View.GONE
            binding.btnExponent2.visibility = View.VISIBLE
        } else {
            binding.btnSecond.visibility = View.GONE
            binding.btnDegree.visibility = View.GONE
            binding.btnSin.visibility = View.GONE
            binding.btnCos.visibility = View.GONE
            binding.btnTan.visibility = View.GONE
            binding.btnPower.visibility = View.GONE
            binding.btnLog.visibility = View.GONE
            binding.btnNaturalLog.visibility = View.GONE
            binding.btnParenthesisStart.visibility = View.GONE
            binding.btnParenthesisClose.visibility = View.GONE
            binding.btnSquareRoot.visibility = View.GONE
            binding.btnFact.visibility = View.GONE
            binding.btnMultiplicativeInverse.visibility = View.GONE
            binding.btnPi.visibility = View.GONE
            binding.btnChangeCalculatorLayout.visibility = View.GONE
            binding.btnChangeCalculator1.visibility = View.GONE
            binding.btnChangeCalculator2.visibility = View.VISIBLE
            binding.btnExponent1.visibility = View.VISIBLE
            binding.btnExponent2.visibility = View.GONE
        }


    }

    private fun changingSecond() {
        if (isSecondEnable) {
            isSecondEnable = false
            binding.btnSin.text = resources.getText(com.example.digitalcalculator.R.string.arcsin)
            binding.btnCos.text = resources.getText(com.example.digitalcalculator.R.string.arccos)
            binding.btnTan.text = resources.getText(com.example.digitalcalculator.R.string.arctan)
            binding.btnDegree.isEnabled = false

        } else {
            isSecondEnable = true
            binding.btnSin.text = "sin "
            binding.btnCos.text = "cos "
            binding.btnTan.text = "tan "
            binding.btnDegree.isEnabled = true

        }
    }

    @SuppressLint("SetTextI18n")
    private fun changingDegree() {
        if (isDegreeEnable) {
            isDegreeEnable = false
            binding.btnSecond.isEnabled = false
            binding.btnDegree.text = "rad"

        } else {
            isDegreeEnable = true
            binding.btnDegree.text = "deg"
            binding.btnSecond.isEnabled = true
        }
    }

    private fun addToTheHistory(currentItem: HistoryAdapterItem) {
        val expression = currentItem.expression
        val result = currentItem.result
        if (expression.isNotEmpty() && result.isNotEmpty()) {

            val history = HistoryAdapterItem(expression, result)
            // adding to database
            historyViewModel =
                ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
            historyViewModel.insert(history.toEntity())
        }
    }

    private fun textToSpeech(text: String) {
        val toggleStateOfOutputVoice = sharedPreferences.getBoolean("output_voice_enabled", false)

        if (toggleStateOfOutputVoice )
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun onSwipeUpToVoiceInput() {
        // Handle swipe up gesture
        val toggleStateOfInputVoice = sharedPreferences.getBoolean("input_voice_enabled", false)

        if (toggleStateOfInputVoice) {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = results?.get(0) ?: ""
            val lastChar = spokenText.last()
            if (lastChar.isDigit()) {

                binding.tvInputCalculation?.setText(spokenText?.filter {
                    it.isDigit() || "+-*/".contains(it)
                })
                equalAction()
            }


        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(com.example.digitalcalculator.R.menu.history, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            com.example.digitalcalculator.R.id.historyFragment -> {
                view?.findNavController()
                    ?.navigate(com.example.digitalcalculator.R.id.action_twoInOneCalculator_to_historyFragment)
            }
        }

        return true
    }

}
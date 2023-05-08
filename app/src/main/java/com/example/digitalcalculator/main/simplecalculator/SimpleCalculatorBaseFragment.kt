package com.example.digitalcalculator.main.simplecalculator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.FragmentSimpleCalculatorBinding
import com.example.digitalcalculator.domain.HistoryAdapterItem
import com.example.digitalcalculator.gestures.SwipeGestureListener
import com.example.digitalcalculator.history.adapter.TempHistoryAdapter
import com.example.digitalcalculator.settings.viewmodel.MyViewModel
import com.example.digitalcalculator.util.PrefUtil
import java.util.*

class SimpleCalculatorBaseFragment : Fragment() {
    private lateinit var binding: FragmentSimpleCalculatorBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var vibrator: Vibrator
    private var canAddOperation = false
    private var canAddDecimal = true
    private var toggleStateOfInputVoice = false
    private val MAX_ITEMS = 3
    private lateinit var currentItem:HistoryAdapterItem
   //  private var lastThreeItems = mutableListOf<HistoryAdapterItem>()
   private lateinit var recyclerView: RecyclerView

    // swipe gesture
    private lateinit var gestureDetector: GestureDetector

    // voice input
    private val SPEECH_REQUEST_CODE = 0

    // text to speech
    private lateinit var textToSpeech: TextToSpeech


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentSimpleCalculatorBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.mainLayout.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }

        setHasOptionsMenu(true)
        recyclerView = binding.recyclerView
        if (savedInstanceState != null) {
            recyclerView.layoutManager?.onRestoreInstanceState(savedInstanceState.getParcelable("recycler_state"))
        }

//        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
//        recyclerView.isNestedScrollingEnabled = false
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        val adapter = TempHistoryAdapter(myViewModel.lastThreeItems)
//        recyclerView.adapter = adapter



        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        binding.btn9.setOnClickListener { numberAction(it) }
        binding.btn8.setOnClickListener { numberAction(it) }
        binding.btn7.setOnClickListener { numberAction(it) }
        binding.btn6.setOnClickListener { numberAction(it) }
        binding.btn5.setOnClickListener { numberAction(it) }
        binding.btn4.setOnClickListener { numberAction(it) }
        binding.btn3.setOnClickListener { numberAction(it) }
        binding.btn2.setOnClickListener { numberAction(it) }
        binding.btn1.setOnClickListener { numberAction(it) }
        binding.btn0.setOnClickListener { numberAction(it) }
        binding.dotBtn.setOnClickListener { dotAction(it) }
        binding.addBtn.setOnClickListener { operationAction(it) }
        binding.subBtn.setOnClickListener { operationAction(it) }
        binding.divBtn.setOnClickListener { operationAction(it) }
        binding.mulBtn.setOnClickListener { operationAction(it) }
        binding.backBtn.setOnClickListener { backSpaceAction(it) }
        binding.clearBtn.setOnClickListener { allClearAction(it) }
        binding.clearBtn.setOnLongClickListener {
            binding.workingsTV.text = ""
            binding.resultsTV.text = ""
//            val tempAdapter=TempHistoryAdapter(lastThreeItems)
//            tempAdapter.clearHistory()
//            tempAdapter.notifyDataSetChanged()
          //  lastThreeItems.clear()
            //Log.i("list", lastThreeItems.toString())
            Toast.makeText(requireContext(), "Long press", Toast.LENGTH_SHORT).show()
            //lastThreeItems.clear()
            true

        }
        binding.equalBtn.setOnClickListener {
            it.isHapticFeedbackEnabled = true
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            equalsAction(it)
        }


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
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        myViewModel.toggleStateOfInputVoice.observe(viewLifecycleOwner) { isOn ->
            toggleStateOfInputVoice = isOn
        }


        //text to speech
        textToSpeech = TextToSpeech(requireContext(), TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.US
            }
        })

    }


    private fun onSwipeUpToVoiceInput() {
        // Handle swipe up gesture
        if (toggleStateOfInputVoice) {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        }


    }

    //voice input getting the txt
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = results?.get(0) ?: ""
            val lastChar = spokenText.last()
            if (lastChar.isDigit()) {

                binding.workingsTV?.setText(spokenText?.filter {
                    it.isDigit() || "+-*/".contains(it)
                })
                equalsAction(requireView())
            }


        }
    }


    fun numberAction(view: View) {

        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.EFFECT_TICK))
        if (binding.resultsTV.text.isEmpty()) {
            if (view is Button) {
                binding.workingsTV.append(view.text)
                canAddOperation = true
            }
        } else {
            binding.resultsTV.text = ""
            binding.workingsTV.text = ""
            if (view is Button) {
                binding.workingsTV.append(view.text)
                canAddOperation = true
            }
            addCalculationToTempHistory()
        }

    }

    private fun dotAction(view: View) {

        if (binding.resultsTV.text.isEmpty()) {
            if (view is Button) {
                if (binding.workingsTV.text.isEmpty()) {
                    binding.workingsTV.text = "0"
                    binding.workingsTV.append(view.text)
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100,
                            VibrationEffect.EFFECT_TICK
                        )
                    )
                    canAddDecimal = false
                } else {
                    binding.workingsTV.append(view.text)
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100,
                            VibrationEffect.EFFECT_TICK
                        )
                    )
                    canAddDecimal = false
                }
            }
        } else {
            binding.resultsTV.text = ""
            binding.workingsTV.text = ""
            if (view is Button) {
                if (binding.workingsTV.text.isEmpty()) {
                    binding.workingsTV.text = "0"
                    binding.workingsTV.append(view.text)
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100,
                            VibrationEffect.EFFECT_TICK
                        )
                    )
                    canAddDecimal = false

                } else {
                    binding.workingsTV.append(view.text)
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100,
                            VibrationEffect.EFFECT_TICK
                        )
                    )
                    canAddDecimal = false
                }
            }
            addCalculationToTempHistory()
            canAddDecimal = true
        }

    }

    private fun addCalculationToTempHistory() {
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)


       // val lastItemAdded= myViewModel.historyItems.last()
        // adding item to the temporary history list
        myViewModel.lastThreeItems.add(currentItem)
       // lastThreeItems.add(lastItemAdded)
        if ( myViewModel.lastThreeItems.size > MAX_ITEMS) {
            myViewModel.lastThreeItems.removeAt(0)
        }
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TempHistoryAdapter(myViewModel.lastThreeItems)
        recyclerView.adapter = adapter
        recyclerView.adapter?.notifyDataSetChanged()
    }

    fun operationAction(view: View) {
        if (view is Button) {
            if (binding.workingsTV.text.isEmpty()) {

                if (view.text == "-") {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100, VibrationEffect.EFFECT_TICK
                        )
                    )
                    binding.workingsTV.append(view.text)
                    canAddOperation = false
                    canAddDecimal = true
                } else if (canAddOperation) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100, VibrationEffect.EFFECT_TICK
                        )
                    )
                    binding.workingsTV.append(view.text)
                    canAddOperation = false
                    canAddDecimal = true
                }
            } else {
                val lastChar = binding.workingsTV.text.last()
                if (view.text == "-" && !"+-/x.".contains(lastChar)) {  //&& lastChar != '-' Todo check for last "-"is present or not
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100, VibrationEffect.EFFECT_TICK
                        )
                    )
                    binding.workingsTV.append(view.text)
                    canAddOperation = false
                    canAddDecimal = true
                } else if (canAddOperation && !"+-/x.".contains(lastChar)) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100, VibrationEffect.EFFECT_TICK
                        )
                    )
                    binding.workingsTV.append(view.text)
                    canAddOperation = false
                    canAddDecimal = true
                }
            }
        }
    }

    fun allClearAction(view: View) {
        if (binding.workingsTV.text.isNotEmpty()) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.EFFECT_TICK))
            binding.workingsTV.text = ""
            binding.resultsTV.text = ""
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun backSpaceAction(view: View) {
        if (binding.workingsTV.text.isNotEmpty() && binding.resultsTV.text.isEmpty()) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.EFFECT_TICK))
            if (binding.resultsTV.text.isEmpty()) {
                val length = binding.workingsTV.length()
                val lastChar = binding.workingsTV.text.last()

                canAddOperation = "-/+x".contains(lastChar)

                if (length > 0)
                    binding.workingsTV.text = binding.workingsTV.text.subSequence(0, length - 1)
            }
        }
    }

    fun equalsAction(view: View) {
        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.EFFECT_TICK))
        binding.resultsTV.text = calculateResults()
        val expression = binding.workingsTV.text.toString()
        val result = binding.resultsTV.text.toString()
        currentItem=HistoryAdapterItem(expression,result)
        addToTheHistory(currentItem)
        textToSpeech.speak(result, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun calculateResults(): String {
        val digitsOperators = digitsOperators()
        if (digitsOperators.isEmpty()) return ""

        val timesDivision = timesDivisionCalculate(digitsOperators)
        if (timesDivision.isEmpty()) return ""

        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+')
                    result += nextDigit
                if (operator == '-')
                    result -= nextDigit
            }
        }

        return result
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('x') || list.contains('/')) {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when (operator) {
                    'x' -> {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if (i > restartIndex)
                newList.add(passedList[i])
        }

        return newList
    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (character in binding.workingsTV.text) {
            if (character.isDigit() || character == '.')
                currentDigit += character
            else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }

        if (currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
    }

    private fun addToTheHistory(currentItem:HistoryAdapterItem) {
        val expression=currentItem.expression
        val result=currentItem.result
        if (expression.isNotEmpty() && result.isNotEmpty()) {
            //passing the main history list to the adapter
            val addingEqualSign = getString(R.string.result, result)
            myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
            myViewModel.historyItems.add(HistoryAdapterItem(expression, addingEqualSign))
        }
        // Log.i("list", myViewModel.historyItems.toString())
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.historyFragment -> {
                view?.findNavController()
                    ?.navigate(R.id.action_simpleCalculatorFragment_to_historyFragment)
            }
        }

        return true
    }



    override fun onStart() {
        super.onStart()

        binding.workingsTV.text = PrefUtil.getPrimaryTextBC(requireContext())
        binding.resultsTV.text = PrefUtil.getSecondaryTextBC(requireContext())
    }

    override fun onStop() {
        super.onStop()

        PrefUtil.setPrimaryTextBC(requireContext(), binding.workingsTV.text.toString())
        PrefUtil.setSecondaryTextBC(requireContext(), binding.resultsTV.text.toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerView = binding.recyclerView

        outState.putParcelable("listState", recyclerView.getLayoutManager()?.onSaveInstanceState())
    }

}




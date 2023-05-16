package com.example.digitalcalculator.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.digitalcalculator.main.TwoInOneCalculator
import com.example.digitalcalculator.main.TwoInOneCalculator.Companion.addedNumber
import com.example.digitalcalculator.main.TwoInOneCalculator.Companion.addedOperator
import com.example.digitalcalculator.main.TwoInOneCalculator.Companion.addedTrigno
import com.example.digitalcalculator.main.TwoInOneCalculator.Companion.equalAction


object ButtonUtil {

    private lateinit var vibrator: Vibrator
    fun addNumberValueToText(
        context: Context,
        buttonId: Button,
        textViewId: TextView,
        backClearBtn: Button,
        tvEqualCalculation: TextView,
        id: Int?
    ) {
        buttonId.setOnClickListener {
            vibratePhone(context)
            textViewId.textSize = 42f
            tvEqualCalculation.textSize = 25f
            if (!equalAction) {
                backClearBtn.isEnabled = true
                textViewId.text = "${textViewId.text}${buttonId.text}"
                addedNumber=true

                when (id) {
                    1 -> {
                        addedTrigno = false
                        addedOperator = false
                    }
                }
                instantEqual(textViewId, tvEqualCalculation)
            } else {
                  equalAction = false
                if (!addedOperator && !addedTrigno) {
                    tvEqualCalculation.text = ""
                    textViewId.text = ""
                    textViewId.text = "${textViewId.text}${buttonId.text}"
                    addedNumber=true
                    instantEqual(textViewId, tvEqualCalculation)
                } else {
                    textViewId.text = "${textViewId.text}${buttonId.text}"
                    addedNumber=true
                    addedOperator=false
                    addedTrigno=false
                    instantEqual(textViewId, tvEqualCalculation)
                }
            }
        }
    }


    fun addScientificValueToText(
        context: Context,
        textId: TextView,
        textViewId: TextView,
        backClearBtn: Button,
        tvEqualCalculation: TextView,
        id: Int?
    ) {
        textId.setOnClickListener {

            textViewId.textSize = 42f
            tvEqualCalculation.textSize = 25f
            vibratePhone(context)
            if (!equalAction) {

                backClearBtn.isEnabled = true
                textViewId.text = "${textViewId.text}${textId.text}"
                when (id) {
                    1 -> {
                        addedOperator = false
                        addedTrigno = true
                    }

                }
            } else {
                tvEqualCalculation.text = ""
                textViewId.text = textId.text
                equalAction=false
            }

        }
    }

    fun addOperatorValueToText(
        context: Context,
        buttonId: Button,
        textViewId: TextView,
        backClearBtn: Button,
        tvEqualCalculation: TextView,
        text: String,
        id: Int
    ) {
        buttonId.setOnClickListener {

            textViewId.textSize = 42f
            tvEqualCalculation.textSize = 25f
            vibratePhone(context)
            backClearBtn.isEnabled = true
            if(textViewId.text.isEmpty() && tvEqualCalculation.text.isEmpty()){
                textViewId.text="0"

            }
            if (!equalAction) {
                when (id) {
                    1 -> {
                        if (addedOperator) textViewId.text =
                            textViewId.text.subSequence(0, textViewId.length() - 1)
                        textViewId.text = textViewId.text.toString() + text
                        addedOperator = true
                        TwoInOneCalculator.addedPointToOperand =false
                    }
                }
            } else {
                val resultOfPreviousCal = tvEqualCalculation.text.toString()
                val result = resultOfPreviousCal.replace(Regex("[^\\d.]"), "")
                textViewId.text = result + text
                addedOperator = true
                TwoInOneCalculator.addedPointToOperand =false
            }
        }
    }

    fun instantEqual(tvInputCalculation: TextView, tvEqualCalculation: TextView) {
        val input = tvInputCalculation.text.toString()
        val checkedExp = CalculationUtil.evaluateResult(input)
        val result = CalculationUtil.evaluate(checkedExp).toString()
        val finalResult = CalculationUtil.trimResult(result)
        tvEqualCalculation.text = "= $finalResult"
    }

    fun vibratePhone(context: Context) {
        vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.EFFECT_TICK))
    }

    fun enterNumberToast(context: Context) {
        Toast.makeText(context, "enter the number", Toast.LENGTH_SHORT).show()
    }

    fun invalidInputToast(context: Context) {
        Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show()
    }
}
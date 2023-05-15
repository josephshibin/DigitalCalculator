package com.example.digitalcalculator.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.digitalcalculator.main.TwoInOneCalculator.Companion.addedOperator
import com.example.digitalcalculator.main.TwoInOneCalculator.Companion.addedTrigno


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
            if (tvEqualCalculation.text.isEmpty()) {
                backClearBtn.isEnabled = true
                textViewId.text = "${textViewId.text}${buttonId.text}"
                when (id) {
                    1 -> {
                        addedTrigno=false
                        addedOperator = false
                    }
                }
            } else {
                if (!addedOperator && !addedTrigno) {
                    tvEqualCalculation.text = ""
                    textViewId.text = ""
                    textViewId.text = "${textViewId.text}${buttonId.text}"
                } else {
                    textViewId.text = "${textViewId.text}${buttonId.text}"
                }
            }
        }
    }


    fun addScientificValueToText(
        context: Context,
        textId: TextView,
        textViewId: TextView,
        backClearBtn: Button,
        id: Int?
    ) {
        textId.setOnClickListener {
            vibratePhone(context)
            backClearBtn.isEnabled = true
            textViewId.text = "${textViewId.text}${textId.text}"
            when (id) {
                1 -> {
                    addedOperator = false
                    addedTrigno = true
                }

            }
        }
    }

    fun addOperatorValueToText(
        context: Context,
        buttonId: Button,
        textViewId: TextView,
        backClearBtn: Button,
        text: String,
        id: Int
    ) {
        buttonId.setOnClickListener {
            vibratePhone(context)
            backClearBtn.isEnabled = true
            when (id) {
                1 -> {
                    if (addedOperator) textViewId.text =
                        textViewId.text.subSequence(0, textViewId.length() - 1)
                    textViewId.text = textViewId.text.toString() + text
                    addedOperator = true
                }
            }
        }
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
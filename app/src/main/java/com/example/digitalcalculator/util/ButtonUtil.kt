package com.example.digitalcalculator.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.digitalcalculator.main.scientficcalculator.ScientificFragment.Companion.addedSC

object ButtonUtil {
    fun addNumberValueToText(context: Context, buttonId: Button, textViewId: TextView, id: Int?) {
        buttonId.setOnClickListener {
            vibratePhone(context)
            textViewId.text = "${textViewId.text}${buttonId.text}"
            when (id) {
                1 -> addedSC = false
            }
        }
    }

    fun addOperatorValueToText(context: Context, buttonId: Button, textViewId: TextView, text: String, id: Int) {
        buttonId.setOnClickListener {
            vibratePhone(context)

            when (id) {
                1 -> {
                    if (addedSC) textViewId.text = textViewId.text.subSequence(0, textViewId.length() - 1)
                    textViewId.text = textViewId.text.toString() + text
                    addedSC = true
                }
            }
        }
    }

    fun vibratePhone(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 29) vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
        else vibrator.vibrate(10)
    }

    fun enterNumberToast(context: Context) {
        Toast.makeText(context, "enter the number", Toast.LENGTH_SHORT).show()
    }

    fun invalidInputToast(context: Context) {
        Toast.makeText(context,"Invalid Input", Toast.LENGTH_SHORT).show()
    }
}
package com.example.digitalcalculator.main

import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.FragmentTwoInOneBinding
import com.example.digitalcalculator.domain.HistoryAdapterItem
import com.example.digitalcalculator.domain.toEntity
import com.example.digitalcalculator.history.historyviewmodel.HistoryViewModel
import com.example.digitalcalculator.main.ScreenUtils.getScreenHeight
import com.example.digitalcalculator.main.scientficcalculator.ScientificFragment
import com.example.digitalcalculator.util.ButtonUtil
import com.example.digitalcalculator.util.CalculationUtil
import kotlin.math.sqrt


class TwoInOneCalculator : Fragment() {
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: FragmentTwoInOneBinding
    private var isScientificCalculator = false
    private var isSecondEnable = true
    private var isDegreeEnable = true


    companion object {
        var addedSC = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentTwoInOneBinding.inflate(layoutInflater, container, false)

        setScreen(0.40)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnOne,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnTwo,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnThree,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnFour,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnFive,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnSix,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnSeven,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnEight,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnNine,
            binding.tvInputCalculation,
            1
        )

        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnSin,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnCos,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnTan,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnLog,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnNaturalLog,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnParenthesisStart,
            binding.tvInputCalculation,
            1
        )
        ButtonUtil.addScientificValueToText(
            requireContext(),
            binding.btnParenthesisClose,
            binding.tvInputCalculation,
            1
        )

        ButtonUtil.addOperatorValueToText(
            requireContext(),
            binding.btnAddition,
            binding.tvInputCalculation,
            "+",
            1
        )
        ButtonUtil.addOperatorValueToText(
            requireContext(),
            binding.btnSubtraction,
            binding.tvInputCalculation,
            "-",
            1
        )
        ButtonUtil.addOperatorValueToText(
            requireContext(),
            binding.btnMultiplication,
            binding.tvInputCalculation,
            "*",
            1
        )
        ButtonUtil.addOperatorValueToText(
            requireContext(),
            binding.btnDivision,
            binding.tvInputCalculation,
            "/",
            1
        )
        binding.btnDot.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())
            if (!binding.tvInputCalculation.text.contains(".")) binding.tvInputCalculation.text =
                binding.tvInputCalculation.text.toString() + "."
        }
        binding.btnFact.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
                else {
                    val input = binding.tvInputCalculation.text.toString()
                    var factorial = 1.0
                    for (i in 1..input.toLong()) {
                        factorial *= i
                    }
                    binding.tvInputCalculation.text = CalculationUtil.trimResult(factorial.toString())
                    binding.tvEqualCalculation.text = "$input!"
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }
        binding.btnSquareRoot.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
                else {
                    val input = binding.tvInputCalculation.text.toString()
                    val result = (input.toFloat() * input.toFloat()).toString()
                    binding.tvInputCalculation.text = CalculationUtil.trimResult(result)
                    binding.tvEqualCalculation.text = "$input²"
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }

        binding.btnMultiplicativeInverse.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
                else {
                    val input = binding.tvInputCalculation.text.toString()
                    val result = (1 / input.toFloat()).toString()
                    binding.tvInputCalculation.text = CalculationUtil.trimResult(result)
                    binding.tvEqualCalculation.text = "1/$input"
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }

        binding.btnSquareRoot.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvInputCalculation.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
                else {
                    val input = binding.tvInputCalculation.text.toString()
                    val result = (sqrt(input.toFloat())).toString()
                    binding.tvInputCalculation.text = CalculationUtil.trimResult(result)
                    binding.tvEqualCalculation.text = "√$input"
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }

        binding.btnAllClear.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            binding.tvInputCalculation.text = ""
            binding.tvEqualCalculation.text = ""
            ScientificFragment.addedSC = false
        }

        binding.btnBackClear.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            if (binding.tvInputCalculation.text.contains("+") ||
                binding.tvInputCalculation.text.contains("-") ||
                binding.tvInputCalculation.text.contains("*") ||
                binding.tvInputCalculation.text.contains("/")
            ) ScientificFragment.addedSC = false

            if (binding.tvInputCalculation.text.isNotEmpty()) binding.tvInputCalculation.text =
                binding.tvInputCalculation.text.subSequence(0, binding.tvInputCalculation.length() - 1)
        }

        binding.btnEqual.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())
            try {
                if (binding.tvInputCalculation.text.isNotEmpty()) {
                    val input = binding.tvInputCalculation.text.toString()
                    val result = CalculationUtil.evaluate(input).toString()
                    binding.tvInputCalculation.text = CalculationUtil.trimResult(result)
                    binding.tvEqualCalculation.text = input
                    ScientificFragment.addedSC = false
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }


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
                    ?.navigate(R.id.action_twoInOneCalculator_to_historyFragment)
            }
        }

        return true
    }

}
//package com.example.digitalcalculator.main.scientficcalculator
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.digitalcalculator.databinding.FragmentScientificBinding
//import com.example.digitalcalculator.util.ButtonUtil.addNumberValueToText
//import com.example.digitalcalculator.util.ButtonUtil.addOperatorValueToText
//import com.example.digitalcalculator.util.ButtonUtil.enterNumberToast
//import com.example.digitalcalculator.util.ButtonUtil.invalidInputToast
//import com.example.digitalcalculator.util.ButtonUtil.vibratePhone
//import com.example.digitalcalculator.util.CalculationUtil
//import com.example.digitalcalculator.util.PrefUtil
//import kotlin.math.sqrt
//
//class ScientificFragment : Fragment() {
//    private lateinit var binding: FragmentScientificBinding
//
//
//    companion object {
//        var addedSC = false
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentScientificBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        addNumberValueToText(requireContext(), binding.bt0SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt1SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt2SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt3SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt4SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt5SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt6SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt7SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt8SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.bt9SC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.btSin, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.btCos, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.btTan, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.btLog, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.btLn, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.btBracketOpenSC, binding.tvPrimarySC, 1)
//        addNumberValueToText(requireContext(), binding.btBracketCloseSC, binding.tvPrimarySC, 1)
//
//        addOperatorValueToText(requireContext(), binding.btAdditionSC, binding.tvPrimarySC, "+", 1)
//        addOperatorValueToText(requireContext(), binding.btSubtractionSC, binding.tvPrimarySC, "-", 1)
//        addOperatorValueToText(requireContext(), binding.btMultiplicationSC, binding.tvPrimarySC, "*", 1)
//        addOperatorValueToText(requireContext(), binding.btDivisionSC, binding.tvPrimarySC, "/", 1)
//
//        binding.btDotSC.setOnClickListener {
//            vibratePhone(requireContext())
//            if (!binding.tvPrimarySC.text.contains(".")) binding.tvPrimarySC.text =
//                binding.tvPrimarySC.text.toString() + "."
//        }
//
//        binding.btPi.setOnClickListener {
//            vibratePhone(requireContext())
//
//            binding.tvPrimarySC.text = binding.tvPrimarySC.text.toString() + "3.142"
//            binding.tvSecondarySC.text = binding.btPi.text.toString()
//        }
//
//        binding.btFactorial.setOnClickListener {
//            vibratePhone(requireContext())
//
//            try {
//                if (binding.tvPrimarySC.text.isEmpty()) enterNumberToast(requireContext())
//                else {
//                    val input = binding.tvPrimarySC.text.toString()
//                    var factorial = 1.0
//                    for (i in 1..input.toLong()) {
//                        factorial *= i
//                    }
//                    binding.tvPrimarySC.text = CalculationUtil.trimResult(factorial.toString())
//                    binding.tvSecondarySC.text = "$input!"
//                }
//            } catch (e: Exception) {
//                invalidInputToast(requireContext())
//            }
//        }
//
//        binding.btSquare.setOnClickListener {
//            vibratePhone(requireContext())
//
//            try {
//                if (binding.tvPrimarySC.text.isEmpty()) enterNumberToast(requireContext())
//                else {
//                    val input = binding.tvPrimarySC.text.toString()
//                    val result = (input.toFloat() * input.toFloat()).toString()
//                    binding.tvPrimarySC.text = CalculationUtil.trimResult(result)
//                    binding.tvSecondarySC.text = "$input²"
//                }
//            } catch (e: Exception) {
//                invalidInputToast(requireContext())
//            }
//        }
//
//        binding.btInverted.setOnClickListener {
//            vibratePhone(requireContext())
//
//            try {
//                if (binding.tvPrimarySC.text.isEmpty()) enterNumberToast(requireContext())
//                else {
//                    val input = binding.tvPrimarySC.text.toString()
//                    val result = (1 / input.toFloat()).toString()
//                    binding.tvPrimarySC.text = CalculationUtil.trimResult(result)
//                    binding.tvSecondarySC.text = "1/$input"
//                }
//            } catch (e: Exception) {
//                invalidInputToast(requireContext())
//            }
//        }
//
//        binding.btSqrt.setOnClickListener {
//            vibratePhone(requireContext())
//
//            try {
//                if (binding.tvPrimarySC.text.isEmpty()) enterNumberToast(requireContext())
//                else {
//                    val input = binding.tvPrimarySC.text.toString()
//                    val result = (sqrt(input.toFloat())).toString()
//                    binding.tvPrimarySC.text = CalculationUtil.trimResult(result)
//                    binding.tvSecondarySC.text = "√$input"
//                }
//            } catch (e: Exception) {
//                invalidInputToast(requireContext())
//            }
//        }
//
//        binding.btACSC.setOnClickListener {
//            vibratePhone(requireContext())
//
//            binding.tvPrimarySC.text = ""
//            binding.tvSecondarySC.text = ""
//            addedSC = false
//        }
//
//        binding.btDeleteSC.setOnClickListener {
//            vibratePhone(requireContext())
//
//            if (binding.tvPrimarySC.text.contains("+") ||
//                binding.tvPrimarySC.text.contains("-") ||
//                binding.tvPrimarySC.text.contains("*") ||
//                binding.tvPrimarySC.text.contains("/")
//            ) addedSC = false
//
//            if (binding.tvPrimarySC.text.isNotEmpty()) binding.tvPrimarySC.text =
//                binding.tvPrimarySC.text.subSequence(0, binding.tvPrimarySC.length() - 1)
//        }
//
//        binding.btEqualSC.setOnClickListener {
//            vibratePhone(requireContext())
//
//            try {
//                if (binding.tvPrimarySC.text.isNotEmpty()) {
//                    val input = binding.tvPrimarySC.text.toString()
//                    val result = CalculationUtil.evaluate(input).toString()
//                    binding.tvPrimarySC.text = CalculationUtil.trimResult(result)
//                    binding.tvSecondarySC.text = input
//                    addedSC = false
//                }
//            } catch (e: Exception) {
//                invalidInputToast(requireContext())
//            }
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        binding.tvPrimarySC.text = PrefUtil.getPrimaryTextSC(requireContext())
//        binding.tvSecondarySC.text = PrefUtil.getSecondaryTextSC(requireContext())
//    }
//
//    override fun onStop() {
//        super.onStop()
//
//        PrefUtil.setPrimaryTextSC(requireContext(), binding.tvPrimarySC.text.toString())
//        PrefUtil.setSecondaryTextSC(requireContext(), binding.tvSecondarySC.text.toString())
//    }
//}
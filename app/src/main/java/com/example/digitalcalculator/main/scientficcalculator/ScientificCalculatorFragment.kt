package com.example.digitalcalculator.main.scientficcalculator

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.digitalcalculator.R
import com.example.digitalcalculator.databinding.FragmentScientificCalculatorBinding

class ScientificCalculatorFragment : Fragment() {
    private lateinit var scientificBinding: FragmentScientificCalculatorBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        scientificBinding =
            FragmentScientificCalculatorBinding.inflate(layoutInflater, container, false)
        return scientificBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adding on click listener to our all buttons.
        scientificBinding.b1.setOnClickListener {
            onNumberAction(it)

        }
        scientificBinding.b2.setOnClickListener {
            // on below line we are appending
            // the expression to our text view.
            // tvMain.text = (tvMain.text.toString() + "2")
            onNumberAction(it)
        }
        scientificBinding.b3.setOnClickListener {
            // on below line we are appending
            // the expression to our text view.
            //tvMain.text = (tvMain.text.toString() + "3")
            onNumberAction(it)
        }
        scientificBinding.b4.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "4")
            onNumberAction(it)
        }
        scientificBinding.b5.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "5")
            onNumberAction(it)
        }
        scientificBinding.b6.setOnClickListener {
            // tvMain.text = (tvMain.text.toString() + "6")
            onNumberAction(it)
        }
        scientificBinding.b7.setOnClickListener {
            // tvMain.text = (tvMain.text.toString() + "7")
            onNumberAction(it)
        }
        scientificBinding.b8.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "8")
            onNumberAction(it)
        }
        scientificBinding.b9.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "9")
            onNumberAction(it)
        }
        scientificBinding.b0.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "0")
            onNumberAction(it)
        }
        scientificBinding.bdot.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + ".")
            onNumberAction(it)
        }
        scientificBinding.bplus.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "+")
            onOperatorAction(it)
        }
        scientificBinding.bdiv.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "/")
            onOperatorAction(it)
        }
        scientificBinding.bbrac1.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "(")
            onExtraKeysAction(it)
        }
        scientificBinding.bbrac2.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + ")")
            onExtraKeysAction(it)
        }
        scientificBinding.bpi.setOnClickListener {
            // on clicking on pi button we are adding
            // pi value as 3.142 to our current value.
            //tvMain.text = (tvMain.text.toString() + "3.142")
            //tvsec.text = (bpi.text.toString())
            onPieAction(it)
        }
        scientificBinding.bsin.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "sin")
            onExtraKeysAction(it)
        }
        scientificBinding.bcos.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "cos")
            onExtraKeysAction(it)
        }
        scientificBinding.btan.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "tan")
            onExtraKeysAction(it)
        }
        scientificBinding.binv.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "^" + "(-1)")
            onInverseFactorAction(it)
        }
        scientificBinding.bln.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "ln")
            onExtraKeysAction(it)
        }
        scientificBinding.blog.setOnClickListener {
            //tvMain.text = (tvMain.text.toString() + "log")
            onExtraKeysAction(it)
        }

        scientificBinding.bminus.setOnClickListener {
            // on clicking on minus we are checking if
            // the user has already a minus operation on screen.
            // if minus operation is already present
            // then we will not do anything.
            //val str: String = tvMain.text.toString()
            // if (!str.get(index = str.length - 1).equals("-")) {
            //tvMain.text = (tvMain.text.toString() + "-")
            // }
            onOperatorAction(it)
        }
        scientificBinding.bmul.setOnClickListener {
            // if mul sign is not present in our
            // text view then only we are adding
            // the multiplication operator to it.
//            val str: String = tvMain.text.toString()
//            if (!str.get(index = str.length - 1).equals("*")) {
//                tvMain.text = (tvMain.text.toString() + "*")
//            }
            onOperatorAction(it)
        }
        scientificBinding.bsqrt.setOnClickListener {
//            if (tvMain.text.toString().isEmpty()) {
//                // if the entered number is empty we are displaying an error message.
//                Toast.makeText(this, "Please enter a valid number..", Toast.LENGTH_SHORT).show()
//            } else {
//                val str: String = tvMain.text.toString()
//                // on below line we are calculation
//                // square root of the given number.
//                val r = Math.sqrt(str.toDouble())
//                // on below line we are converting our double
//                // to string and then setting it to text view.
//                val result = r.toString()
//                tvMain.setText(result)
//            }
            onSquareRootAction(it)
        }
        scientificBinding.bequal.setOnClickListener {
            val str: String = scientificBinding.idTVprimary.text.toString()
            // on below line we are calling an evaluate
            // method to calculate the value of expressions.
            val result: Double = evaluate(str)
            // on below line we are getting result
            // and setting it to text view.
            val r = result.toString()
            scientificBinding.idTVprimary.setText(r)
            scientificBinding.idTVSecondary.text = str
        }
        scientificBinding.bac.setOnClickListener {
            // on clicking on ac button we are clearing
            // our primary and secondary text view.
            scientificBinding.idTVprimary.setText("")
            scientificBinding.idTVSecondary.setText("")
        }
        scientificBinding.bc.setOnClickListener {
            //  on clicking on c button we are clearing
            // the last character by checking the length.
            var str: String = scientificBinding.idTVprimary.text.toString()
            if (!str.equals("")) {
                str = str.substring(0, str.length - 1)
                scientificBinding.idTVprimary.text = str
            }
        }
        scientificBinding.bsquare.setOnClickListener {
            if (scientificBinding.idTVprimary.text.toString().isEmpty()) {
                // if the entered number is empty we are displaying an error message.
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid number..",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // on below line we are getting the expression and then calculating the square of the number
                val d: Double = scientificBinding.idTVprimary.getText().toString().toDouble()
                // on below line we are calculating the square.
                val square = d * d
                // after calculating the square we
                // are setting it to text view.
                scientificBinding.idTVprimary.setText(square.toString())
                // on below line we are setting
                // the d to secondary text view.
                scientificBinding.idTVSecondary.text = "$d²"
            }
        }
        scientificBinding.bfact.setOnClickListener {
            if (scientificBinding.idTVprimary.text.toString().isEmpty()) {
                // if the entered number is empty we are displaying an error message.
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid number..",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // on below line we are getting int value
                // and calculating the factorial value of the entered number.
                val value: Int = scientificBinding.idTVprimary.text.toString().toInt()
                val fact: Int = factorial(value)
                scientificBinding.idTVprimary.text = fact.toString()
                scientificBinding.idTVSecondary.text = "$value`!"
            }

        }

    }

    private fun onNumberAction(view: View) {
        if (view is Button) {
            scientificBinding.idTVprimary.append(view.text)
        }
    }

    private fun onExtraKeysAction(view: View) {
        if (view is Button) {
            scientificBinding.idTVprimary.append(view.text)
        }
    }

    private fun onInverseFactorAction(view: View) {
        if (view is Button) {
            scientificBinding.idTVprimary.append("^")
            scientificBinding.idTVprimary.append("-1")
        }
    }

    private fun onPieAction(view: View) {
        if (view is Button) {
            //tvMain.text = (tvMain.text.toString() + "3.142")
            //tvsec.text = (bpi.text.toString())
            scientificBinding.idTVprimary.append("3.142")
            scientificBinding.idTVSecondary.append(view.text)

        }
    }

    private fun onOperatorAction(view: View) {
        if (view is Button) {
            if (scientificBinding.idTVprimary.text.isNotEmpty()) {
                val chr: Char = scientificBinding.idTVprimary.text.toString().last()
                if (!"+-÷×.".contains(chr)) {
                    scientificBinding.idTVprimary.append(view.text)
                }
            } else {
                scientificBinding.idTVprimary.append(view.text)
            }
        }

    }

    private fun onSquareRootAction(view: View) {
        if (scientificBinding.idTVprimary.text.toString().isEmpty()) {
            // if the entered number is empty we are displaying an error message.

            Toast.makeText(requireContext(), "Please enter a valid number..", Toast.LENGTH_SHORT)
                .show()
        } else {
            val str: String = scientificBinding.idTVprimary.text.toString()
            // on below line we are calculation
            // square root of the given number.
            val r = Math.sqrt(str.toDouble())
            // on below line we are converting our double
            // to string and then setting it to text view.
            val result = r.toString()
            scientificBinding.idTVprimary.text = result
        }
    }

    private fun factorial(n: Int): Int {
        // this method is use to find factorial
        return if (n == 1 || n == 0) 1 else n * factorial(n - 1)
    }

    private fun evaluate(str: String): Double {
        return object : Any() {
            // on below line we are creating variable
            // for tracking the position and char pos.
            var pos = -1
            var ch = 0

            // below method is for moving to next character.
            fun nextChar() {
                // on below line we are incrementing our position
                // and moving it to next position.
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            // this method is use to check the extra space
            // present int the expression and removing it.
            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                // on below line we are checking the char pos
                // if both is equal then we are returning it to true.
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            // below method is to parse our
            // expression and to get the ans
            // in this we are calling a parse
            // expression method to calculate the value.
            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            // in this method we will only perform addition and
            // subtraction operation on the expression.
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.toInt())) x += parseTerm() // addition
                    else if (eat('-'.toInt())) x -= parseTerm() // subtraction
                    else return x
                }
            }

            // in below method we will perform
            // only multiplication and division operation.
            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt())) x *= parseFactor() // multiplication
                    else if (eat('/'.toInt())) x /= parseFactor() // division
                    else return x
                }
            }

            // below method is use to parse the factor
            fun parseFactor(): Double {
                //on below line we are checking for addition
                // and subtraction and performing unary operations.
                if (eat('+'.toInt())) return parseFactor() // unary plus
                if (eat('-'.toInt())) return -parseFactor() // unary minus
                // creating a double variable for ans.
                var x: Double
                // on below line we are creating
                // a variable for position.
                val startPos = pos
                // on below line we are checking
                // for opening and closing parenthesis.
                if (eat('('.toInt())) { // parentheses
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) {
                    // numbers
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    // on below line we are getting sub string from our string using start and pos.
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) {
                    // on below function we are checking for the operator in our expression.
                    while (ch >= 'a'.toInt() && ch <= 'z'.toInt()) nextChar()
                    val func = str.substring(startPos, pos)
                    // calling a method to parse our factor.
                    x = parseFactor()
                    // on below line we are checking for square root.
                    x =
                        if (func == "sqrt") Math.sqrt(x)
                        // on below line we are checking for sin function
                        // and calculating sin function using Math class.
                        else if (func == "sin") Math.sin(
                            Math.toRadians(x)
                            // on below line we are calculating the cos value
                        ) else if (func == "cos") Math.cos(
                            Math.toRadians(x)
                            // on below line we are calculating
                            // the tan value of our expression.
                        ) else if (func == "tan")
                            Math.tan(Math.toRadians(x))
                        // on below line we are calculating
                        // log value of the expression.
                        else if (func == "log")
                            Math.log10(x)
                        // on below line we are calculating
                        // ln value of expression.
                        else if (func == "ln") Math.log(x)
                        // f we get any error then
                        // we simply return the exception.
                        else throw RuntimeException(
                            "Unknown function: $func"
                        )
                } else {
                    // if the condition not satisfy then we are returning the exception
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                // on below line we are calculating the power of the expression.
                if (eat('^'.toInt())) x = Math.pow(x, parseFactor()) // exponentiation
                return x
            }
            // at last calling a parse for our expression.
        }.parse()
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
                //TODO
            }
        }

        return true
    }
}
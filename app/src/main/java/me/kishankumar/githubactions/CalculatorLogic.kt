package me.kishankumar.githubactions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CalculatorLogic {
    var display by mutableStateOf("0")
        private set

    private var firstOperand: Double? = null
    private var pendingOperation: String? = null
    private var isNewInput = true

    fun onDigitInput(digit: String) {
        if (display == "Error" || isNewInput) {
            display = if (digit == ".") "0." else digit
            isNewInput = false
        } else {
            if (digit == "." && display.contains(".")) return
            display += digit
        }
    }

    fun onOperationInput(op: String) {
        val currentNumber = display.toDoubleOrNull() ?: return
        firstOperand = currentNumber
        pendingOperation = op
        isNewInput = true
    }

    fun onEquals() {
        val op = pendingOperation ?: return
        val first = firstOperand ?: return
        val second = display.toDoubleOrNull() ?: return

        val result = when (op) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> if (second == 0.0) Double.NaN else first / second
            else -> second
        }

        display = if (result.isNaN()) {
            "Error"
        } else {
            formatResult(result)
        }

        firstOperand = null
        pendingOperation = null
        isNewInput = true
    }

    fun onClear() {
        display = "0"
        firstOperand = null
        pendingOperation = null
        isNewInput = true
    }

    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toLong().toString()
        } else {
            value.toString()
        }
    }
}

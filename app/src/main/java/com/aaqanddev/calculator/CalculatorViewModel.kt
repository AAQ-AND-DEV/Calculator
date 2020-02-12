package com.aaqanddev.calculator

import androidx.lifecycle.ViewModel


class CalculatorViewModel : ViewModel() {
    //variable to hold operands and type of calculation
    private var operand1: Double? = null
    private var pendingOperation = "="

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value

        } else {

            if (pendingOperation == "=") {
                pendingOperation = operation
            }
            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN //Handle attempt to divide by 0
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }

        result.setText(operand1.toString())
        newNumber.setText("")
    }
}
package com.aaqanddev.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException


//keys for operation and result in bundle
private const val OPERATION_KEY = "Operation"
private const val OPERAND1_KEY = "Operand1"
private const val STATE_OPERAND1_STORED = "operand1Stored"

class MainActivity : AppCompatActivity() {
//    private lateinit var result: EditText
//    private lateinit var newNumber: EditText
//    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    //variable to hold operands and type of calculation
    private var operand1: Double? = null
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        result = findViewById(R.id.result)
//        newNumber = findViewById(R.id.newNumber)

//        Data input buttons
//        val button0 = findViewById<Button>(R.id.button0)
//        val button1 = findViewById<Button>(R.id.button1)
//        val button2 = findViewById<Button>(R.id.button2)
//        val button3 = findViewById<Button>(R.id.button3)
//        val button4 = findViewById<Button>(R.id.button4)
//        val button5 = findViewById<Button>(R.id.button5)
//        val button6 = findViewById<Button>(R.id.button6)
//        val button7 = findViewById<Button>(R.id.button7)
//        val button8 = findViewById<Button>(R.id.button8)
//        val button9 = findViewById<Button>(R.id.button9)
//        val buttonDot = findViewById<Button>(R.id.buttonDot)

        //Operation Buttons
//        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
//        val buttonDivide = findViewById<Button>(R.id.buttonDiv)
//        val buttonMult = findViewById<Button>(R.id.buttonMult)
//        val buttonMinus = findViewById<Button>(R.id.buttonSub)
//        val buttonPlus = findViewById<Button>(R.id.buttonAdd)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, op)

            } catch (e: NumberFormatException) {
                newNumber.setText("")
            }
            pendingOperation = op
            operation.text = pendingOperation
        }
        buttonEquals.setOnClickListener(opListener)
        buttonDiv.setOnClickListener(opListener)
        buttonMult.setOnClickListener(opListener)
        buttonSub.setOnClickListener(opListener)
        buttonAdd.setOnClickListener(opListener)

        val negListener = View.OnClickListener { v ->
            if (newNumber.text.isNotEmpty()){
                try{
                    var value = newNumber.text.toString().toDouble()
                    value = value!! * -1.0
                    //operand1 = value
                    newNumber.setText(value.toString())

                } catch (e: NumberFormatException){
                    newNumber.setText("")
                }
            } else{
                newNumber.setText("-")
            }
        }
        buttonNeg.setOnClickListener(negListener)

        buttonClear.setOnClickListener {
            operand1 = null
            result.setText("")
            newNumber.setText("")
            operation.setText("")
            pendingOperation = "="

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(OPERATION_KEY, pendingOperation)
        //outState.putString(OPERAND1_KEY, operand1.toString())
        if (operand1 != null){
            outState.putDouble(OPERAND1_KEY, operand1!!)
            outState.putBoolean(STATE_OPERAND1_STORED, true)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        operand1 = if (savedInstanceState.getBoolean(STATE_OPERAND1_STORED,false)){
            savedInstanceState.getDouble(OPERAND1_KEY)
        } else{
            null
        }
        pendingOperation = savedInstanceState.getString(OPERATION_KEY).toString()

        //operand1 = savedInstanceState.getString(OPERAND1_KEY).toString().toDouble()
        operation.text = pendingOperation

    }

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

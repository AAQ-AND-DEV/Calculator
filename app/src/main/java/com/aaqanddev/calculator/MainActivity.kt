package com.aaqanddev.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

        val negListener = View.OnClickListener {
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




}

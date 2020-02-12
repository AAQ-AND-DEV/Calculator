package com.aaqanddev.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel : CalculatorViewModel by viewModels()

        viewModel.stringResult.observe(this, Observer<String> {stringResult -> result.setText(stringResult)})
        viewModel.stringNewNumber.observe(this, Observer<String> {stringNum -> newNumber.setText(stringNum)})
        viewModel.stringOperation.observe(this, Observer<String> {stringOp -> operation.text = stringOp})

        val listener = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
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
            viewModel.operandPressed((v as Button).text.toString())

        }
        buttonEquals.setOnClickListener(opListener)
        buttonDiv.setOnClickListener(opListener)
        buttonMult.setOnClickListener(opListener)
        buttonSub.setOnClickListener(opListener)
        buttonAdd.setOnClickListener(opListener)

        val negListener = View.OnClickListener {
            viewModel.negPressed()

        }
        buttonNeg.setOnClickListener(negListener)



        buttonClear.setOnClickListener {
            viewModel.clearPressed()

        }
    }




}

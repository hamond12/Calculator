package com.example.calculator

import Calculator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.exp

class CalculatorActivity : AppCompatActivity() {
    val calculator = Calculator()

    private lateinit var expression: TextView
    private lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()

        expression = findViewById(R.id.txt_expression)
        result = findViewById(R.id.txt_result)
    }

    fun onClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()
        val expressionText = expression.text.toString()

        when (buttonText) {
            "AC" -> {
                expression.text = ""
                result.text = ""
            }
            "DEL" -> {
                if (expressionText.isNotEmpty()) {
                    expression.text = expressionText.substring(0, expressionText.length - 1)
                }
            }
            "+", "-", "x", "÷" -> {
                if(expressionText.isNotEmpty() && expressionText.last() !in "+-x÷"){
                    expression.text = expressionText + buttonText
                }
            }
            "=" -> {
                if(expressionText.isNotEmpty()){
                    val res = calculator.calculate(expressionText)
                    result.text = "= " + res.toString()
                }
            }
            else -> expression.text = expressionText + buttonText
        }
    }
}
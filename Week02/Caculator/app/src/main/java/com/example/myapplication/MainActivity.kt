package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnAdd: Button
    lateinit var btnSubtract: Button
    lateinit var btnMultiply: Button
    lateinit var btnDivide: Button
    lateinit var txtName1: EditText
    lateinit var txtName2: EditText
    lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)

        txtName1 = findViewById(R.id.txtName2)
        txtName2 = findViewById(R.id.txtName1)
        txtResult = findViewById(R.id.txtResult)

        btnAdd.setOnClickListener(this)
        btnSubtract.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivide.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val number1 = txtName1.text.toString().toDoubleOrNull()
        val number2 = txtName2.text.toString().toDoubleOrNull()
        if (number1 == null || number2 == null) {
            txtResult.text = "Please enter two numbers"
            return
        }
        var result = 0.0
        when (v?.id) {
            R.id.btnAdd -> {
                result = number1 + number2
            }
            R.id.btnSubtract -> {
                result = number1 - number2
            }
            R.id.btnMultiply -> {
                result = number1 * number2
            }
            R.id.btnDivide -> {
                if (number2 == 0.0) {
                    txtResult.text = "Cannot divide by zero"
                    return
                }
                result = number1 / number2
            }
        }
        txtResult.text = "Result: $result"
    }
}
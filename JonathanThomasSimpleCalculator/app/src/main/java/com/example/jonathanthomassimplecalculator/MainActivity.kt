package com.example.jonathanthomassimplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val num1v = findViewById<EditText>(R.id.num1)
        val num2v = findViewById<EditText>(R.id.num2)

        val operand = findViewById<Spinner>(R.id.operand)

        val calcButton = findViewById<Button>(R.id.calcbutton)

        val result = findViewById<TextView>(R.id.result)

        calcButton.setOnClickListener {

            val num1 = num1v.text.toString().toDoubleOrNull()
            val num2 = num2v.text.toString().toDoubleOrNull()

            var ans = 0.0

            if (num1 != null && num2 != null) {
                if (operand.selectedItem.toString() == "+") {
                    ans = num1 + num2
                } else if (operand.selectedItem.toString() == "-") {
                    ans = num1 - num2
                }
                result.text = "Result:  $ans"
            } else {
                result.text = "Please enter valid input!"
            }
        }

    }

}
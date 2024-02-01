package com.example.jonathanthomasguicalculator

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.StringBuilder
import java.util.LinkedList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val banner = findViewById<TextView>(R.id.banner)
        val calcResult = findViewById<EditText>(R.id.calc_result)

        val button1 = findViewById<Button>(R.id.button41)
        val button2 = findViewById<Button>(R.id.button42)
        val buttonplus = findViewById<Button>(R.id.button44)
        val buttonequals = findViewById<Button>(R.id.button54)

        var currentInput = StringBuilder("")

        val inputQueue: LinkedList<String> = LinkedList()

        button1.setOnClickListener {
            calcResult.append("1")
            currentInput.append("1")
            //banner.text = inputQueue.toString()
        }

        button2.setOnClickListener {
            calcResult.append("2")
            currentInput.append("2")
            //banner.text = inputQueue.toString()
        }

        buttonplus.setOnClickListener {
            calcResult.append("+")
            if (currentInput.isNotBlank()) {
                inputQueue.add(currentInput.toString())
            }
            inputQueue.add("+")
            currentInput.clear()
            banner.text = inputQueue.toString()
        }

        buttonequals.setOnClickListener {
            inputQueue.add(currentInput.toString())
            currentInput.clear()
            val res = evaluate(inputQueue)
            banner.text = inputQueue.toString()
            calcResult.setText(res)
            //currentInput.append(res)
        }

        calcResult.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Perform your action here
                val userInput = calcResult.text.toString()
                // Do something with userInput
                banner.text = "banana"
                return@OnKeyListener true // Consume the event
            }
            false // Let the system handle other events
        })

    }

    private fun evaluate(inputQueue: LinkedList<String>): String {

        var ans = 0.0

        while (inputQueue.size > 1) {

            val num1 = inputQueue.poll().toDouble()
            val op = inputQueue.poll()
            val num2 = inputQueue.poll().toDouble()

            if (op == "+") {
                ans = num1 + num2
            } else if (op == "-") {
                ans = num1 - num2
            } else if (op == "×") {
                ans = num1 * num2
            } else if (op == "÷") {
                if (num2 == 0.0) {
                } else {
                    ans = num1 / num2
                }
            } else
                if (num2 == 0.0) {
                } else {
                    ans = num1 % num2

                }
            inputQueue.addFirst(ans.toString())
        }

        return ans.toString()
    }
}
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
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val banner = findViewById<TextView>(R.id.banner)
        val calcResult = findViewById<EditText>(R.id.calc_result)

        val button1 = findViewById<Button>(R.id.button41)
        val button2 = findViewById<Button>(R.id.button42)
        val button3 = findViewById<Button>(R.id.button43)
        val button4 = findViewById<Button>(R.id.button31)
        val button5 = findViewById<Button>(R.id.button32)
        val button6 = findViewById<Button>(R.id.button33)
        val button7 = findViewById<Button>(R.id.button21)
        val button8 = findViewById<Button>(R.id.button22)
        val button9 = findViewById<Button>(R.id.button23)
        val button0 = findViewById<Button>(R.id.button51)
        val buttondot = findViewById<Button>(R.id.button52)

        val buttonplus = findViewById<Button>(R.id.button44)
        val buttonminus = findViewById<Button>(R.id.button34)
        val buttonmul = findViewById<Button>(R.id.button24)
        val buttondiv = findViewById<Button>(R.id.button14)
        val buttonmod = findViewById<Button>(R.id.button13)
        val buttonequals = findViewById<Button>(R.id.button54)
        val buttonsqrt = findViewById<Button>(R.id.button12)

        val buttondel = findViewById<Button>(R.id.button53)
        val buttonac = findViewById<Button>(R.id.button11)

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

        button3.setOnClickListener {
            calcResult.append("3")
            currentInput.append("3")
            //banner.text = inputQueue.toString()
        }

        button4.setOnClickListener {
            calcResult.append("4")
            currentInput.append("4")
            //banner.text = inputQueue.toString()
        }

        button5.setOnClickListener {
            calcResult.append("5")
            currentInput.append("5")
            //banner.text = inputQueue.toString()
        }

        button6.setOnClickListener {
            calcResult.append("6")
            currentInput.append("6")
            //banner.text = inputQueue.toString()
        }

        button7.setOnClickListener {
            calcResult.append("7")
            currentInput.append("7")
            //banner.text = inputQueue.toString()
        }

        button8.setOnClickListener {
            calcResult.append("8")
            currentInput.append("8")
            //banner.text = inputQueue.toString()
        }

        button9.setOnClickListener {
            calcResult.append("9")
            currentInput.append("9")
            //banner.text = inputQueue.toString()
        }

        button0.setOnClickListener {
            calcResult.append("0")
            currentInput.append("0")
            //banner.text = inputQueue.toString()
        }

        buttondot.setOnClickListener {
            calcResult.append(".")
            currentInput.append(".")
            //banner.text = inputQueue.toString()
        }

        buttonplus.setOnClickListener {
            calcResult.append("+")
            if (currentInput.isNotBlank()) {
                inputQueue.add(currentInput.toString())
            }
            inputQueue.add("+")
            currentInput.clear()
            //banner.text = inputQueue.toString()
        }

        buttonminus.setOnClickListener {
            calcResult.append("-")
            if (currentInput.isNotBlank()) {
                inputQueue.add(currentInput.toString())
            }
            inputQueue.add("-")
            currentInput.clear()
            //banner.text = inputQueue.toString()
        }

        buttonmul.setOnClickListener {
            calcResult.append("*")
            if (currentInput.isNotBlank()) {
                inputQueue.add(currentInput.toString())
            }
            inputQueue.add("*")
            currentInput.clear()
            //banner.text = inputQueue.toString()
        }

        buttondiv.setOnClickListener {
            calcResult.append("/")
            if (currentInput.isNotBlank()) {
                inputQueue.add(currentInput.toString())
            }
            inputQueue.add("/")
            currentInput.clear()
            //banner.text = inputQueue.toString()
        }

        buttonmod.setOnClickListener {
            calcResult.append("%")
            if (currentInput.isNotBlank()) {
                inputQueue.add(currentInput.toString())
            }
            inputQueue.add("%")
            currentInput.clear()
            //banner.text = inputQueue.toString()
        }

        buttonequals.setOnClickListener {
            inputQueue.add(currentInput.toString())
            currentInput.clear()
            val res = evaluate(inputQueue)
            //banner.text = inputQueue.toString()
            calcResult.setText(res)
            //currentInput.append(res)
        }

        buttonsqrt.setOnClickListener {
            var sqrt = sqrt(currentInput.toString().toDouble()).toString()
            calcResult.setText(sqrt)
            inputQueue.add(sqrt)
            currentInput.clear()

        }

        buttonac.setOnClickListener {
            inputQueue.clear()
            currentInput.clear()
            calcResult.text.clear()
            //banner.text = inputQueue.toString()
        }

        buttondel.setOnClickListener {
            if (calcResult.text.toString().isNotEmpty()) {
                val last = calcResult.text.toString().last()
                if (last == '+' || last == '-' || last == '*' || last == '/' || last == '%') {
                    inputQueue.removeLast()
                } else
                    if (currentInput.isNotEmpty()) {
                        currentInput.deleteCharAt(currentInput.length - 1)
                    } else
                        calcResult.setText(calcResult.text.toString().dropLast(1))
            }
            calcResult.setText(calcResult.text.toString().dropLast(1))
            //banner.text = inputQueue.toString()
            //banner.text = currentInput.toString()
        }


        calcResult.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                inputQueue.clear()
                val res = evaluateString(inputQueue, calcResult.text.toString())
                calcResult.setText(res)
                //banner.text = inputQueue.toString()
                return@OnKeyListener true // Consume the event

            }/* else if (event.action == KeyEvent.ACTION_DOWN && isNumberKey(keyCode)) {
                val numberPressed = keyCode - KeyEvent.KEYCODE_0
                banner.text = "banana"
                currentInput.append(numberPressed.toString())
                banner.text = inputQueue.toString()
                return@OnKeyListener true

            } else if (event.action == KeyEvent.ACTION_DOWN && /*isOperatorKey(keyCode)*/keyCode == 81) {
                val operatorPressed = getOperatorPressed(keyCode)
                banner.text = "banana"
                /*if (currentInput.isNotBlank()) {
                    inputQueue.add(currentInput.toString())
                }
                inputQueue.add(operatorPressed)
                currentInput.clear()
                banner.text = inputQueue.toString()*/
                return@OnKeyListener true

            } else*/
            false // Let the system handle other events
        })


    }

    private fun evaluateString(inputQueue: LinkedList<String>, calc: String): String {

        for (char in calc) {
            inputQueue.add(char.toString())
        }
        return evaluate(inputQueue)
    }
    /*
    private fun isNumberKey(keyCode: Int): Boolean {
        return keyCode in KeyEvent.KEYCODE_0..KeyEvent.KEYCODE_9
    }

    private fun isOperatorKey(keyCode: Int): Boolean {
        return keyCode == KeyEvent.KEYCODE_PLUS ||
                keyCode == KeyEvent.KEYCODE_MINUS ||
                keyCode == KeyEvent.KEYCODE_STAR ||
                keyCode == KeyEvent.KEYCODE_SLASH
    }

    private fun getOperatorPressed(keyCode: Int): String {
        return when (keyCode) {
            KeyEvent.KEYCODE_PLUS -> "+"
            KeyEvent.KEYCODE_MINUS -> "-"
            KeyEvent.KEYCODE_STAR -> "*"
            KeyEvent.KEYCODE_SLASH -> "/"
            else -> throw IllegalArgumentException("Invalid operator key code")
        }
    }*/

    private fun evaluate(inputQueue: LinkedList<String>): String {

        var ans = 0.0
        val precision = 3
        val str = ""

        while (inputQueue.size > 1) {

            val num1 = inputQueue.poll().toDouble()
            val op = inputQueue.poll()
            val num2 = inputQueue.poll().toDouble()

            if (op == "+") {
                ans = num1 + num2
            } else if (op == "-") {
                ans = num1 - num2
            } else if (op == "*") {
                ans = num1 * num2
            } else if (op == "/") {
                if (num2 == 0.0) {
                } else {
                    ans = num1 / num2
                }
            } else
                if (num2 == 0.0) {
                } else {
                    ans = num1 % num2

                }
            val str = String.format("%.${precision}f", ans)
            inputQueue.addFirst(str)
            //inputQueue.clear()
        }

        return String.format("%.${precision}f", ans)
    }

}
package com.example.jonathanthomasguicalculator

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
//import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.LinkedList
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val banner = findViewById<TextView>(R.id.banner)
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

        button1.setOnClickListener {
            calcResult.append("1")
        }

        button2.setOnClickListener {
            calcResult.append("2")
        }

        button3.setOnClickListener {
            calcResult.append("3")
           //Log.d("eval", evaluateString("-2*-3"))
        }

        button4.setOnClickListener {
            calcResult.append("4")
        }

        button5.setOnClickListener {
            calcResult.append("5")
        }

        button6.setOnClickListener {
            calcResult.append("6")
        }

        button7.setOnClickListener {
            calcResult.append("7")
        }

        button8.setOnClickListener {
            calcResult.append("8")
        }

        button9.setOnClickListener {
            calcResult.append("9")
        }

        button0.setOnClickListener {
            calcResult.append("0")
        }

        buttondot.setOnClickListener {
            calcResult.append(".")
        }

        buttonplus.setOnClickListener {
            calcResult.append("+")
        }

        buttonminus.setOnClickListener {
            calcResult.append("-")
        }

        buttonmul.setOnClickListener {
            calcResult.append("*")
        }

        buttondiv.setOnClickListener {
            calcResult.append("/")
        }

        buttonmod.setOnClickListener {
            calcResult.append("%")
        }

        buttonequals.setOnClickListener {
            val res = evaluateString(calcResult.text.toString())
            calcResult.setText(res)
        }

        buttonsqrt.setOnClickListener {
            val precision = 2
            val sqrt = String.format("%.${precision}f", sqrt(calcResult.text.toString().toDouble()))
            calcResult.setText(sqrt)
        }

        buttonac.setOnClickListener {
            calcResult.text.clear()
        }

        buttondel.setOnClickListener {
            if (calcResult.text.toString().isNotEmpty()) {
                val res = calcResult.text.toString().dropLast(1)
                calcResult.setText(res)
            }
        }


        calcResult.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val res = evaluateString(calcResult.text.toString())
                calcResult.setText(res)
                return@OnKeyListener true // Consume the event

            }
            false // Let the system handle other events
        })


    }

    private fun isOperator(str: String): Boolean {
        return str == "+" || str == "-" || str == "*" || str == "/" || str == "%"
    }

    /*private fun createCopy(calc: String): LinkedList<String> {

        val res: LinkedList<String> = LinkedList()

        for (index in calc.indices) {
            if (isOperator(calc[index])) {
                if (calc[index] == '-') {
                    if (index == 0)
                        res.add("0")
                res.add(calc[index].toString())
                }
            } else
                str += char
        }
        return res
    }*/

    private fun evaluateString(calc: String): String {

        val res: LinkedList<String> = LinkedList()

        var minus = false

        var inputs = calc.split("(?<=\\+)|(?=\\+)|(?<=\\*)|(?=\\*)|(?<=\\-)|(?=\\-)|(?<=\\/)|(?=\\/)|(?<=\\%)|(?=\\%)".toRegex())
        inputs = inputs.filterNot{ it.isBlank()}
        Log.d("inputs",inputs.toString())

        for (index in inputs.indices) {
            if (inputs[index] == "-" && index == 0)
                minus = true
            else if (inputs[index] == "-" && index > 0) {
                if (isOperator(inputs[index - 1]))
                    minus = true
                else
                    res.add(inputs[index])
            } else
                if (minus == true) {
                    res.add("-" + inputs[index])
                    minus = false
                } else
                    res.add(inputs[index])
            //Log.d("minus",minus.toString())
            Log.d("res",res.toString())
        }
        return evaluate(res)
    }

    private fun evaluate(inputQueue: LinkedList<String>): String {

        var ans = 0.0
        val precision = 2

        while (inputQueue.size > 1) {

            val num1 = inputQueue.poll().toDouble()
            val op = inputQueue.poll()
            val num2 = inputQueue.poll().toDouble()

            if (op == "+") {
                ans = num1 + num2
            } else if (op == "-") {
                Log.d("Num1", num1.toString())
                Log.d("Num2", num2.toString())
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
            inputQueue.addFirst(ans.toString())
        }

        return String.format("%.${precision}f", ans)
    }

}
package com.example.jonathanthomassnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        trueButton.setOnClickListener {
            var sb = Snackbar.make(findViewById(R.id.textView), R.string.correct_snackbar, 1000)
            sb.show()
        }

        falseButton.setOnClickListener {
            var sb = Snackbar.make(findViewById(R.id.textView), R.string.incorrect_snackbar, 1000)
            sb.show()
        }
    }
}
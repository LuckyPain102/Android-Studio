package com.example.assignment1

import android.os.Bundle
import android.util.Log
import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity(){
    private lateinit var txtScore: TextView
    private lateinit var txtElement: TextView
    private lateinit var btnPerform: Button
    private lateinit var btnDeduction: Button
    private lateinit var btnReset: Button

    private var score = 0
    private var element = 1
    private var routineEnded = false

    companion object{
        private const val TAG = "GymnasticsApp"
        private const val KEY_SCORE = "score"
        private const val KEY_ELEMENT = "element"
        private const val KEY_ENDED = "routineEnded"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtScore = findViewById(R.id.txtScore)
        txtElement = findViewById(R.id.txtElement)
        btnPerform = findViewById(R.id.btnPerform)
        btnDeduction = findViewById(R.id.btnDeduction)
        btnReset = findViewById(R.id.btnReset)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(KEY_SCORE, 0)
            element = savedInstanceState.getInt(KEY_ELEMENT, 1)
            routineEnded =
                savedInstanceState.getBoolean(KEY_ENDED, false)
        }

        btnPerform.setOnClickListener {
            Log.d(TAG, "Perform button clicked")

            if (routineEnded) {
                return@setOnClickListener
            }

            val points = when (element) {
                in 1..3 -> 1
                in 4..7 -> 2
                in 8..10 -> 3
                else -> 0
            }

            score = (score + points).coerceAtMost(20)

            Log.d(TAG, "Element $element performed")
            Log.d(TAG, "Score updated: $score")

            if (element == 10) {
                routineEnded = true

                Toast.makeText(
                    this,
                    "Routine complete!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                element++
            }

            updateUI()
        }

        btnDeduction.setOnClickListener {
            Log.d(TAG, "Deduction button clicked")

            if (element == 1) {
                Toast.makeText(
                    this,
                    "Complete Element 1 first!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (routineEnded) {
                return@setOnClickListener
            }

            score = (score - 2).coerceAtLeast(0)
            routineEnded = true

            Log.d(TAG, "Deduction applied. Score: $score")

            Toast.makeText(
                this,
                "Deduction applied. Routine ended!",
                Toast.LENGTH_SHORT
            ).show()

            updateUI()
        }

        btnReset.setOnClickListener {
            Log.d(TAG, "Reset button clicked")

            score = 0
            element = 1
            routineEnded = false

            Log.d(TAG, "Routine reset")

            updateUI()
        }

        updateUI()
    }

    private fun updateUI() {
        txtScore.text = "Score: $score"

        txtElement.text = if (routineEnded) {
            if (element == 10 && score > 0) {
                "Routine complete!"
            } else {
                "Routine ended"
            }
        } else {
            "Element: $element / 10"
        }

        val scoreColor = when (element) {
            in 1..3 -> Color.BLUE
            in 4..7 -> Color.GREEN
            else -> Color.rgb(255, 165, 0)
        }

        txtScore.setTextColor(scoreColor)

        btnPerform.isEnabled = !routineEnded
        btnDeduction.isEnabled = element > 1 && !routineEnded
        btnReset.isEnabled = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_SCORE, score)
        outState.putInt(KEY_ELEMENT, element)
        outState.putBoolean(KEY_ENDED, routineEnded)
        super.onSaveInstanceState(outState)
    }
}
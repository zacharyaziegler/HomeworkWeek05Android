package com.example.quizapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class Question1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        val sharedPreference =  getSharedPreferences("STATS", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        var earned = 0
        var wrong = 0
        var right = 0
        editor.putInt("earned", earned)
        editor.putInt("wrong", wrong)
        editor.putInt("right", right)
        editor.commit()

        val confirmBtn = findViewById<Button>(R.id.btn_confirm)
        val radioGroup = findViewById<RadioGroup>(R.id.rg_q1)
        val tv_earned = findViewById<TextView>(R.id.tv_earned)
        tv_earned.text = "You Earned: $$earned"


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            for (i in 0 until group.childCount) {
                val radioButton = group.getChildAt(i) as RadioButton
                if (radioButton.id == checkedId) {
                    // Selected RadioButton
                    radioButton.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rb_select, null))
                } else {
                    // Unselected RadioButton
                    radioButton.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.splash_color, null))
                }
            }
        }


        confirmBtn.setOnClickListener {
            val selectedOption: Int = radioGroup!!.checkedRadioButtonId
            if (selectedOption == -1) {
                Toast.makeText(this@Question1Activity, "ERROR! Must select an option", Toast.LENGTH_SHORT).show()
            } else {
                val radioButton = findViewById<RadioButton>(selectedOption)

                if (radioButton.text == "Smith") {
                    earned += 100
                    right += 1
                    editor.putInt("earned", earned)
                    editor.putInt("right", right)
                    editor.commit()
                    tv_earned.text = "You Earned: $$earned"
                    Toast.makeText(
                        this@Question1Activity,
                        "CORRECT! You've earned $100!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@Question1Activity,
                        "WRONG! The correct answer was Smith",
                        Toast.LENGTH_SHORT
                    ).show()
                    wrong += 1
                    editor.putInt("wrong", wrong)
                    editor.commit()
                }

                val i = Intent(this, Question2Activity::class.java) // create intent for question 2
                startActivity(i)
                finish()
            }



            }
        }
    }


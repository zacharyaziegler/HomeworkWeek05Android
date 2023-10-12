package com.example.quizapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

class Question3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question3)

        val sharedPreference =  getSharedPreferences("STATS", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        var earned = sharedPreference.getInt("earned", 0)
        var wrong = sharedPreference.getInt("wrong", 0)
        var right = sharedPreference.getInt("right", 0) // get shared pref and update values for stats

        val confirmBtn = findViewById<Button>(R.id.btn_confirm)
        val radioGroup = findViewById<RadioGroup>(R.id.rg_q1)
        val tv_earned = findViewById<TextView>(R.id.tv_earned) // get radio group, button and textview for events
        tv_earned.text = "You Earned: $$earned" // update earned text view


        radioGroup.setOnCheckedChangeListener { group, checkedId -> // radiogroup listener that sees which button is selected
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
            if (selectedOption == -1) { // prevent no option selected
                Toast.makeText(this@Question3Activity, "ERROR! Must select an option", Toast.LENGTH_SHORT).show()
            } else {
                val radioButton = findViewById<RadioButton>(selectedOption)

                if (radioButton.text == "Australia") { // if correct
                    earned+=400 // money earned
                    right+=1 // right answer increase for stats
                    editor.putInt("earned", earned) // put in sharedprefs
                    editor.putInt("right", right)
                    editor.commit()
                    tv_earned.text = "You Earned: $$earned"
                    Toast.makeText(this@Question3Activity, "CORRECT! You've earned $400!", Toast.LENGTH_SHORT).show()
                } else { // if incorrect
                    Toast.makeText(this@Question3Activity, "WRONG! The correct answer was Australia", Toast.LENGTH_SHORT).show()
                    wrong+=1
                    editor.putInt("wrong", wrong)
                    editor.commit()
                }

                val i = Intent(this, Question4Activity::class.java) // create intent
                startActivity(i)
                finish()
            }

        }
    }
}
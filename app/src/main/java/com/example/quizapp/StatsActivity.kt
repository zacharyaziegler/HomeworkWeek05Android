package com.example.quizapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class StatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        val tvMoneyEarned = findViewById<TextView>(R.id.tv_money_earned)
        val tvRight = findViewById<TextView>(R.id.tv_right)
        val tvWrong = findViewById<TextView>(R.id.tv_wrong)
        val sharedPreference =  getSharedPreferences("STATS", Context.MODE_PRIVATE) // get shared prefs for stats
        val moneyEarned = sharedPreference.getInt("earned", 0) // get each stat
        val right = sharedPreference.getInt("right", 0)
        val wrong = sharedPreference.getInt("wrong", 0)
        tvMoneyEarned.text = "Money Earned: $$moneyEarned" // set each stat to be displayed
        tvRight.text = "Correct Answers: $right"
        tvWrong.text = "Incorrect Answers: $wrong"
    }
}
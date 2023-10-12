package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.btn_start)

        startBtn.setOnClickListener{
            val i = Intent(this, Question1Activity::class.java) // create intent of main activity to launch after animation
            startActivity(i)
            finish()
        }
    }
}
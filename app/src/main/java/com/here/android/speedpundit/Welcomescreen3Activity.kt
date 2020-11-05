package com.here.android.speedpundit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Welcomescreen3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcomescreen3)
        val button7 = findViewById<Button>(R.id.button7)
        val button9 = findViewById<Button>(R.id.button9)
        val button10 = findViewById<Button>(R.id.button10)


        button7.setOnClickListener {
            val intent = Intent(this, Welcomescreen1Activity::class.java)

            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right)
        }

        button9.setOnClickListener {
            val intent = Intent(this, Welcomescreen3Activity::class.java)

            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right)
        }

        button10.setOnClickListener {
            val intent = Intent(this, maps::class.java)

            startActivity(intent)

        }
    }
}
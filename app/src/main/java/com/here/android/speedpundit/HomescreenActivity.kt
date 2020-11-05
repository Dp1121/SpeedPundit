package com.here.android.speedpundit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class HomescreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    //Include Motion Graphics For Background In MainActivity 1
    // TODO: 11/3/2020
    //Fill UI
    // TODO: 11/3/2020

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)
        auth = FirebaseAuth.getInstance()
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)

        button2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }

        button.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)

            startActivity(intent)

        }
    }
}
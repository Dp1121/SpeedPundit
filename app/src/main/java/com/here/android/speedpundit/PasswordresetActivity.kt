package com.here.android.speedpundit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordresetActivity : AppCompatActivity() {
    //Fix Password Reset
    // TODO: 11/3/2020

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passwordreset)
        auth = FirebaseAuth.getInstance()
        val button9 = findViewById<Button>(R.id.button9)
        val button11 = findViewById<Button>(R.id.button11)
        val username = findViewById<EditText>(R.id.email_password_reset)

        button11.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        button9.setOnClickListener {
            forgotPassword(username)
        }
    }


    private fun forgotPassword(username: EditText) {
        if (username.text.toString().isEmpty()) {
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            return
        }

        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email sent.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
    }
}
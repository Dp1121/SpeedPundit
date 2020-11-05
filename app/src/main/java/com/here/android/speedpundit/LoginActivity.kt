package com.here.android.speedpundit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    //Fix Login
    // TODO: 11/3/2020

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        button6.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()

            button5.setOnClickListener {
                doLogin()
            }
        }



        button7.setOnClickListener {
            startActivity(Intent(this, PasswordresetActivity::class.java))
            finish()
        }


    }

    private fun doLogin() {
        if (tv_emailL.text.toString().isEmpty()) {
            tv_emailL.error = "Please enter email"
            tv_emailL.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tv_emailL.text.toString()).matches()) {
            tv_emailL.error = "Please enter valid email"
            tv_emailL.requestFocus()
            return
        }

        if (tv_password.text.toString().isEmpty()) {
            tv_password.error = "Please enter password"
            tv_password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(tv_emailL.text.toString(), tv_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }
            }
    }



    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, maps::class.java))
                finish()
            }else{
                startActivity(Intent(this, maps::class.java))
                finish()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
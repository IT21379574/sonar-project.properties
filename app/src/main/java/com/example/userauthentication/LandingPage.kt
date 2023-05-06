package com.example.userauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LandingPage : AppCompatActivity() {
    private lateinit var btnGetStarted : Button

    private lateinit var auth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        auth = Firebase.auth

        btnGetStarted = findViewById(R.id.btnGetStart)

        btnGetStarted.setOnClickListener { getStart() }
    }

    private fun getStart() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}
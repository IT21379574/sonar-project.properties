package com.example.userauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogout: Button
    private lateinit var btnProfile: CardView
    private lateinit var btnOrders: CardView

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

        btnLogout = findViewById(R.id.btnLogout)
        btnProfile = findViewById(R.id.btnProfile)
        btnOrders = findViewById(R.id.btnOrders)
        user = auth.currentUser!!

        if (user == null){
            gotoLogin()
        }

        btnLogout.setOnClickListener { logout() }
        btnProfile.setOnClickListener { gotoProfile() }
        btnOrders.setOnClickListener { gotoOrders() }
    }

    private fun gotoOrders() {
        val intent = Intent(this, Order::class.java)
        startActivity(intent)
        finish()
    }

    private fun gotoProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }

    private fun logout() {
        Firebase.auth.signOut()
        gotoLogin()
    }

    private fun gotoLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}
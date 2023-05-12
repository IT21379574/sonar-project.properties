package com.example.paymentmad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createPay(view: View) {
        val intent = Intent(this,AddPayment::class.java)
        startActivity(intent)
    }

    fun viewHistory(view: View) {
        val intent = Intent(this,FetchingData::class.java)
        startActivity(intent)
    }
}
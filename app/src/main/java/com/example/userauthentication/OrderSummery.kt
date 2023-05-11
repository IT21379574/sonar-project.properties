package com.example.userauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class OrderSummery : AppCompatActivity() {


    private lateinit var odersumbackbtn: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summery)

        odersumbackbtn = findViewById(R.id.odersumbackbtn)

        odersumbackbtn.setOnClickListener { back() }
    }

    private fun back() {
        val intent = Intent(this, Order::class.java)
        startActivity(intent)
        finish()
    }
}
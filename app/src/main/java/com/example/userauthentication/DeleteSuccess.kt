package com.example.paymentmad

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DeleteSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success_activity)
    }
    override fun onBackPressed() {
        // Do nothing to disable the back button
    }

    fun goHome(view: View) {
        val intent = Intent(this,FetchingData::class.java)
        startActivity(intent)
    }

}
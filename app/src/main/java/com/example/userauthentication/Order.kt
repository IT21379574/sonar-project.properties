package com.example.userauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class Order : AppCompatActivity() {
    private lateinit var tvPrice: TextView
    private lateinit var tvNewPrice: TextView
    private lateinit var cb1: CheckBox
    private lateinit var cb2: CheckBox
    private lateinit var edPromo: EditText
    private lateinit var btnApply: Button
    private lateinit var btnProceed: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        tvPrice = findViewById(R.id.tvPrice)
        tvNewPrice = findViewById(R.id.tvNewPrice)
        cb1 = findViewById(R.id.checkBox)
        cb2 = findViewById(R.id.checkBox2)
        edPromo = findViewById(R.id.Promo_code)
        btnApply = findViewById(R.id.apply)
        btnProceed = findViewById(R.id.procced)

        tvPrice.text = "50"
        tvNewPrice.text = tvPrice.text

        cb1.setOnClickListener { checkbox1Click() }
        cb2.setOnClickListener { checkbox2Click() }
        btnApply.setOnClickListener { apply() }
    }

    private fun checkbox2Click() {
        if (cb2.isChecked){
            tvNewPrice.text = (tvNewPrice.text.toString().toInt() + 30).toString()
        }else{
            tvNewPrice.text = (tvNewPrice.text.toString().toInt() - 30).toString()
        }
    }

    private fun checkbox1Click() {
        if (cb1.isChecked){
            tvNewPrice.text = (tvNewPrice.text.toString().toInt() + 25).toString()
        }else{
            tvNewPrice.text = (tvNewPrice.text.toString().toInt() - 25).toString()
        }
    }

    private fun apply() {
        TODO("Not yet implemented")
    }
}
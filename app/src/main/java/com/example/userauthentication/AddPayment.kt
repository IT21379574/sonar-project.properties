package com.example.paymentmad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddPayment : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    private lateinit var cusName: EditText
    private lateinit var cusAddress: EditText
    private lateinit var cusMobile: EditText
    private lateinit var price: EditText
    private lateinit var cType: EditText
    private lateinit var cNumber: EditText
    private lateinit var cName: EditText
    private lateinit var cCvc: EditText
    private lateinit var cExDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_payment_activity)

        dbRef = FirebaseDatabase.getInstance().getReference("paymentData")
        cusName = findViewById(R.id.cusname)
        cusAddress = findViewById(R.id.cusAddress)
        cusMobile = findViewById(R.id.cusMobile)
        price = findViewById(R.id.payPrice)
        cType = findViewById(R.id.cardType)
        cNumber = findViewById(R.id.cardNumber)
        cName = findViewById(R.id.cardUserName)
        cCvc = findViewById(R.id.cardcvc)
        cExDate = findViewById(R.id.cardExDate)
    }

    fun createPay(view: View) {
        val name = cusName.text.toString()
        val address = cusAddress.text.toString()
        val mobile = cusMobile.text.toString()
        val price = price.text.toString()
        val type = cType.text.toString()
        val number = cNumber.text.toString()
        val nameCard = cName.text.toString()
        val cvc = cCvc.text.toString()
        val date = cExDate.text.toString()

        if (type.isNotEmpty() && number.isNotEmpty() && nameCard.isNotEmpty() && cvc.isNotEmpty() && date.isNotEmpty() ){

            val paymentId = dbRef.push().key!!

            val company = ClassPayment(
                payId = paymentId,
                name = name,
                address = address,
                mobile = mobile,
                price = price,
                type = type,
                cardNum = number,
                cardName = nameCard,
                cvc = cvc,
                expireDate = date

            )

            dbRef.child(paymentId).setValue(company)
                .addOnCompleteListener {
                    Toast.makeText(this, "Payment successful", Toast.LENGTH_LONG).show()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
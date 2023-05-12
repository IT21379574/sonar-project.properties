package com.example.paymentmad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class EditPayment : AppCompatActivity(){
    private lateinit var uName: EditText
    private lateinit var uAddress: EditText
    private lateinit var uMobile: EditText
    private lateinit var price: EditText
    private lateinit var cType: EditText
    private lateinit var cNumber: EditText
    private lateinit var cName: EditText

    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_info_activity)
        databaseReference = FirebaseDatabase.getInstance().getReference("paymentData")
        initView()
        setValuesToViews()

    }

    private fun initView() {
        uName = findViewById(R.id.getName)
        uAddress = findViewById(R.id.getAddress)
        uMobile = findViewById(R.id.getMobile)
        price = findViewById(R.id.getPrice)
        cType = findViewById(R.id.getType)
        cNumber = findViewById(R.id.getCNumber)
        cName = findViewById(R.id.getCName)

    }

    private fun setValuesToViews() {
        uName.setText(intent.getStringExtra("name"))
        uAddress.setText(intent.getStringExtra("address"))
        uMobile.setText(intent.getStringExtra("mobile"))
        price.setText(intent.getStringExtra("price"))
        cType.setText(intent.getStringExtra("type"))
        cNumber.setText(intent.getStringExtra("cardNum"))
        cName.setText(intent.getStringExtra("cardName"))

    }

    fun updatePayment(view: View) {
        val name = uName.text.toString()
        val address = uAddress.text.toString()
        val mobile = uMobile.text.toString()
        val price = price.text.toString()
        val type = cType.text.toString()
        val number = cNumber.text.toString()
        val namecard = cName.text.toString()
        val cvc = intent.getStringExtra("cvc").toString()
        val exDate = intent.getStringExtra("expireDate").toString()
        val docId = intent.getStringExtra("id").toString()
        if (name.isNotEmpty() && type.isNotEmpty() && namecard.isNotEmpty() && price.isNotEmpty() ){

            val classPayment = ClassPayment(
                payId = docId,
                name = name,
                address = address,
                mobile = mobile ,
                price = price,
                type = type,
                cardNum = number,
                cardName = namecard,
                cvc = cvc,
                expireDate = exDate

            )

            databaseReference.child(docId).setValue(classPayment)
                .addOnCompleteListener {
                    Toast.makeText(this, "Payment Updated", Toast.LENGTH_LONG).show()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
    fun deletePayment(view: View) {
        val docId = intent.getStringExtra("id").toString()

        if (docId.isNotEmpty()){
            val intent = Intent(this, DeleteSuccess::class.java)
            databaseReference.child(docId).setValue(null)
                .addOnCompleteListener {
                    Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

    }

}
package com.example.userauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Profile : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPhone: EditText
    private lateinit var btnEdit: Button
    private lateinit var btnChangePassword: Button
    private lateinit var btnDeleteAccount: Button
    private lateinit var btnProfileBack: ImageView

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private lateinit var dbref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = Firebase.auth

        edName = findViewById(R.id.name)
        edEmail = findViewById(R.id.email)
        edPhone = findViewById(R.id.phone)

        btnEdit = findViewById(R.id.btnEdit)
        btnChangePassword = findViewById(R.id.btnChangePassword)
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount)
        btnProfileBack = findViewById(R.id.btnProfileBack)
        user = auth.currentUser!!





        if (user == null){
            gotoLogin()
        }else{
            edName.setText(user.displayName)
            edEmail.setText(user.email)
            edPhone.setText(user.phoneNumber)
        }

        btnEdit.setOnClickListener { edit() }
        btnChangePassword.setOnClickListener { changePassword() }
        btnDeleteAccount.setOnClickListener { deleteAccount() }
        btnProfileBack.setOnClickListener { back() }
    }

    private fun back() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun gotoLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    private fun deleteAccount() {
        val user = Firebase.auth.currentUser!!

        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, Registration::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }

    private fun changePassword() {
        val intent = Intent(this, ChangePassword::class.java)
        startActivity(intent)
        finish()
    }

    private fun edit() {
        val name = edName.text.toString()
        val email = edEmail.text.toString()
        val phone = edPhone.text.toString()

        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) { }
            }

        user!!.updateEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) { }
            }

        Toast.makeText(this, "Successfully edited", Toast.LENGTH_SHORT).show()
    }
}
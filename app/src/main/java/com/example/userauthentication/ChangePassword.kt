package com.example.userauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePassword : AppCompatActivity() {
    private lateinit var edCurrentPassword: EditText
    private lateinit var edNewPassword: EditText
    private lateinit var edConfirmPassword: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnChangeBack: ImageView

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        auth = Firebase.auth

        edCurrentPassword = findViewById(R.id.currentPassword)
        edNewPassword = findViewById(R.id.newPassword)
        edConfirmPassword = findViewById(R.id.confirmPassword)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnChangeBack = findViewById(R.id.btnChangeBack)
        user = auth.currentUser!!

        if (user == null){
            gotoLogin()
        }

        btnUpdate.setOnClickListener { updatePassword() }
        btnChangeBack.setOnClickListener { gotoProfile() }
    }

    private fun gotoProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }

    private fun updatePassword() {
        val currentPassword = edCurrentPassword.text.toString()
        val newPassword = edNewPassword.text.toString()
        val confirmPassword = edConfirmPassword.text.toString()

        if (currentPassword.isEmpty()){
            Toast.makeText(this, "please enter current password", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword.isEmpty()){
            Toast.makeText(this, "please enter new password", Toast.LENGTH_SHORT).show()
            return
        }

        if (confirmPassword.isEmpty()){
            Toast.makeText(this, "please enter confirm password", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword.compareTo(confirmPassword) != 0){
            Toast.makeText(this, "please enter confirm password and new password not equal", Toast.LENGTH_SHORT).show()
            return
        }

        val user = Firebase.auth.currentUser

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "successfully updated the password", Toast.LENGTH_SHORT).show()
                    edCurrentPassword.text.clear()
                    edNewPassword.text.clear()
                    edConfirmPassword.text.clear()

                }
            }
    }

    private fun gotoLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}
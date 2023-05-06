package com.example.madproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLogTags.Description
import android.widget.Toast
import com.example.realtimedatabasekotlin.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {

            val userName = binding.Jobtitle.text.toString()
            val firstName = binding.Description.text.toString()
            val lastName = binding.Price.text.toString()

            updateData(Jobtitle,Description,Price)

        }

    }

    private fun updateData(Jobtitle: String, Description: String, Price: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
            "Jobtitle" to Jobtitle,
            "Description" to Description,
            "Price" to Price,
        )

        database.child(Jobtitle).updateChildren(user).addOnSuccessListener {

            binding.Jobtitle.text.clear()
            binding.Description.text.clear()
            binding.Price.text.clear()
            Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }}
}
package com.example.madproject

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.madproject.databinding.ActivityMain3Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var binding : ActivityMain3Binding
    private lateinit var database : DatabaseReference

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView8.setOnClickListener {

            val Jobtitle = binding.edtJobTitle.text.toString()
            val Description = binding.edtDesc.text.toString()
            val Price = binding.edtPrice.text.toString()


            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(Jobtitle,Description,Price)
            database.child(Jobtitle).setValue(User).addOnSuccessListener {

                binding.edtJobTitle.text.clear()
                binding.edtDesc.text.clear()
                binding.edtPrice.text.clear()


                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }


        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}
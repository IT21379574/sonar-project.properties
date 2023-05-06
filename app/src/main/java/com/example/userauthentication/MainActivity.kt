package com.example.madproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import com.example.madproject.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //SEARCH bar
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Perform search operation here
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Filter search results here
                return true
            }
        })


    }
}
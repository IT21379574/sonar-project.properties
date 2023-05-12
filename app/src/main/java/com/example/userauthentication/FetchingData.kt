package com.example.paymentmad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Madjobs.HelperAdapter
import com.google.firebase.database.*

class FetchingData : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var arrayList: ArrayList<ClassPayment>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetching_activity)

        recyclerView = findViewById(R.id.recyclerId)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.loadingId)

        arrayList = arrayListOf<ClassPayment>()

        retriveList()

    }

    private fun retriveList() {

        recyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        databaseReference = FirebaseDatabase.getInstance().getReference("paymentData")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(ClassPayment::class.java)
                        arrayList.add(empData!!)
                    }
                    val mAdapter = HelperAdapter(arrayList)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : HelperAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingData, EditPayment::class.java)
                            intent.putExtra("id", arrayList[position].payId)
                            intent.putExtra("name", arrayList[position].name)
                            intent.putExtra("address", arrayList[position].address)
                            intent.putExtra("mobile", arrayList[position].mobile)
                            intent.putExtra("price", arrayList[position].price)
                            intent.putExtra("type", arrayList[position].type)
                            intent.putExtra("cardNum", arrayList[position].cardNum)
                            intent.putExtra("cardName", arrayList[position].cardName)
                            intent.putExtra("cvc", arrayList[position].cvc)
                            intent.putExtra("expireDate", arrayList[position].expireDate)
                            startActivity(intent)
                        }

                    })

                    recyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
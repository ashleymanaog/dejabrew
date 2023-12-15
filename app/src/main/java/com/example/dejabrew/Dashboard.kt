package com.example.dejabrew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Dashboard : AppCompatActivity() {
    private lateinit var coffeecard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        coffeecard = findViewById(R.id.bankingcard)
        coffeecard.setOnClickListener {
            val intent = Intent(this, Recipe::class.java)
            startActivity(intent)
        }
//class Dashboard : AppCompatActivity() {
//    private lateinit var coffeecard: CardView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dashboard)
//
//        val database = FirebaseDatabase.getInstance()
//        val itemsRef = database.getReference("items")
//
//        itemsRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val items = dataSnapshot.children.mapNotNull { it.getValue(UploadClass::class.java) }
//                items.forEach { item ->
//                     coffeecard = findViewById(R.id.bankingcard)
//
//                    val cardView = CardView(this@Dashboard)
//                    // Set up the card view with the item data.
//                    coffeecard.setOnClickListener {
//                        val intent = Intent(this@Dashboard, Recipe::class.java)
//                        intent.putExtra("item_id", item.item_id)
//                        startActivity(intent)
//                    }
//                    // Add the card view to your layout.
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Handle possible errors.
//            }
//        })
//        class Recipe : AppCompatActivity() {
//            override fun onCreate(savedInstanceState: Bundle?) {
//                super.onCreate(savedInstanceState)
//                setContentView(R.layout.activity_recipe)
//
//                val itemId = intent.getStringExtra("item_id")
//                // Use the itemId to fetch the item data from the database.
//            }
//        }

        val aboutbtn = findViewById<Button>(R.id.aboutbtn)
        aboutbtn.setOnClickListener {
            val Intent = Intent(this, About::class.java)
            startActivity(Intent)
        }
        val eprofilebtn = findViewById<Button>(R.id.editbtn)
        eprofilebtn.setOnClickListener {
            val Intent = Intent(this, EditProfile::class.java)
            startActivity(Intent)
        }

        val uploadbtn = findViewById<Button>(R.id.uploadbtn)
        uploadbtn.setOnClickListener {
            val Intent = Intent(this, Upload::class.java)
            startActivity(Intent)
        }


    }

}
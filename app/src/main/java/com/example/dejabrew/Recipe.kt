package com.example.dejabrew

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.storage


import com.squareup.picasso.Picasso;
class Recipe : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    private lateinit var rImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        val itemId = intent.getStringExtra("item_id")
        // Use the itemId to fetch the item data from the database.

        val backButton = findViewById<Button>(R.id.Backbtn)
        backButton.setOnClickListener {
            val Intent = Intent(this, Dashboard::class.java)
            startActivity(Intent)

        }


        // Get a reference to the database
        databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseName")
        var editEmpName: TextView = findViewById(R.id.txtEmpRecipeTitle)
        var editRecipe: TextView = findViewById(R.id.txtEmpRecipeText)


        databaseReference.child("Recipe").child("8GUgSsqi1s").child("title_text")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val title_text = dataSnapshot.getValue(String::class.java)
                        editEmpName.setText(title_text)
                    } else {
                        Log.d("MainActivity", "No data available")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("MainActivity", "Failed to read value.", databaseError.toException())
                }
            })
        databaseReference.child("Recipe").child("8GUgSsqi1s").child("recipe_text")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val recipe_text = dataSnapshot.getValue(String::class.java)
                        editRecipe.setText(recipe_text)
                    } else {
                        Log.d("MainActivity", "No data available")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("MainActivity", "Failed to read value.", databaseError.toException())
                }
            })
        // RETRIEVE PHOTO
        // Reference to an image file in Cloud Storage
//        val storageReference = Firebase.storage.reference
//        // ImageView in your Activity
//        val imageView = findViewById<ImageView>(R.id.recPhotoName)
//        // Download directly from StorageReference using Glide
//        GlideApp.with(this)
//            .load(storageReference)
//            .into(imageView)
//
// Reference to an image file in Cloud Storage
//        val storageReference = Firebase.storage.reference.child("gs://dejabrew-cb1c0.appspot.com/1000000033")
//        // ImageView in your Activity
//        val imageView = findViewById<ImageView>(R.id.recPhotoName)
//        // Download directly from StorageReference using Bitmap
//        storageReference.getBytes(10 * 1024 * 1024).addOnSuccessListener { bytes ->
//            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//            imageView.setImageBitmap(bitmap)
//        }.addOnFailureListener { exception ->
//            // Handle any errors
//            val errorCode = (exception as StorageException).errorCode
//            val errorMessage = exception.message
//            Log.e(
//                "MainActivity",
//                "Failed to read value. Error code: $errorCode, Error message: $errorMessage"
//            )
//        }
        rImage = findViewById(R.id.recPhotoName)

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.getReference()
        val getImage = databaseReference.child("image")
        getImage.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val link = dataSnapshot.getValue(String::class.java)
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/dejabrew-cb1c0.appspot.com/o/1000000033?alt=media&token=cd438d6d-7979-442c-8ac1-2926f0a5951d").into(rImage)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@Recipe, "Error Loading Image", Toast.LENGTH_SHORT).show()
            }
        })
    }


}
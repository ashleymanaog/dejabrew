package com.example.dejabrew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class Upload : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        val backButton = findViewById<Button>(R.id.uploadBackbtn)
        backButton.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }

        val buttonRegister: Button = findViewById(R.id.uploadbtn)

        buttonRegister.setOnClickListener{
            performUpload()
        }



    }

    private fun performUpload() {
        //uid
        val generatedIds = mutableSetOf<String>()
        fun generateRandomId(length: Int): String {
            val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            return (1..length)
                .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
                .joinToString("")
        }

        var id = generateRandomId(10)
        while (id in generatedIds) {
            id = generateRandomId(10)
        }
        generatedIds.add(id)

        //TEXT
        val title_upload = findViewById<EditText>(R.id.edtTitle)
        val recipe_upload = findViewById<EditText>(R.id.edtRecipe)
        val title_upload2 = title_upload.text.toString()
        val recipe_upload2 = recipe_upload.text.toString()
        //Add
        lateinit var databaseReference: DatabaseReference

        databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseName")
        var databaseClass = UploadClass(id, title_upload2, recipe_upload2)
        var dataKey = databaseReference.push().getKey();
        databaseReference.child("Recipe").child(id).setValue(databaseClass)
            .addOnSuccessListener()
            {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
    }
    }

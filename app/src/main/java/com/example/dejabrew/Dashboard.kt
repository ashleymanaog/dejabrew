package com.example.dejabrew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class Dashboard : AppCompatActivity() {
    private lateinit var bankingcard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        bankingcard = findViewById(R.id.bankingcard)
        bankingcard.setOnClickListener {
            val intent = Intent(this, Recipe::class.java)
            startActivity(intent)
        }

        val aboutbtn = findViewById<Button>(R.id.aboutbtn)
        aboutbtn.setOnClickListener {
            val Intent = Intent(this, About::class.java)
            startActivity(Intent)
        }

        val editprofilebtn = findViewById<Button>(R.id.editprofilebtn)
        editprofilebtn.setOnClickListener {
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
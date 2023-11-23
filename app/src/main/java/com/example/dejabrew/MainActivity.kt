package com.example.dejabrew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.Loginbtn)
        loginButton.setOnClickListener {
            val Intent = Intent(this, Login::class.java)
            startActivity(Intent)
        }
        val registerButton = findViewById<Button>(R.id.Registerbtn)
        registerButton.setOnClickListener {
            val Intent = Intent(this, Profile2::class.java)
            startActivity(Intent)
        }

    }
}
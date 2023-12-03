package com.example.dejabrew

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var databaseReference : DatabaseReference

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //            var editEmpName: TextView = findViewById(R.id.txtEmpName)

        val loginButton = findViewById<Button>(R.id.Loginbtn)
        loginButton.setOnClickListener {
            val Intent = Intent(this, Login::class.java)
            startActivity(Intent)
        }
        val registerButton = findViewById<Button>(R.id.Registerbtn)
        registerButton.setOnClickListener {
            val Intent = Intent(this, Register::class.java)
            startActivity(Intent)
        }

  //Add
            databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseName")
            var databaseClass = DatabaseClass("Pascua", "SSE", "BizAps")
            var dataKey = databaseReference.push().getKey();
            databaseReference.child("Pascua").setValue(databaseClass).addOnSuccessListener()
            {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }

        val aboutbtn = findViewById<Button>(R.id.aboutbtn)
        aboutbtn.setOnClickListener {
            val Intent = Intent(this, About::class.java)
            startActivity(Intent)
        }

//        val editprofilebtn = findViewById<Button>(R.id.editprofilebtn)
//        editprofilebtn.setOnClickListener {
//            val Intent = Intent(this, EditProfile::class.java)
//            startActivity(Intent)
//        }
//
//        val uploadbtn = findViewById<Button>(R.id.uploadbtn)
//        uploadbtn.setOnClickListener {
//            val Intent = Intent(this, Upload::class.java)
//            startActivity(Intent)
//        }


    }
}
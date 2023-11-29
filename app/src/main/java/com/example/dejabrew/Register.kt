package com.example.dejabrew

import android.annotation.SuppressLint
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var databaseReference : DatabaseReference

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val buttonRegister: Button = findViewById(R.id.btnRegister)

        buttonRegister.setOnClickListener{
            performRegister()
        }
    }

    private fun performRegister(){
        //DECLARATIONS OF VARIABLES
        val regFirstName = findViewById<EditText>(R.id.regFirstName)
        val regLastName = findViewById<EditText>(R.id.regLastName)
        val regEmail = findViewById<EditText>(R.id.regEmail)
        val regPassword = findViewById<EditText>(R.id.regPassword)

        if (regEmail.text.isEmpty() || regPassword.text.isEmpty()){
            Toast.makeText(this, "Kindly fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val inputRegFirstName = regFirstName.text.toString()
        val inputRegLastName = regLastName.text.toString()
        val userName = inputRegFirstName +" "+ inputRegLastName
        val inputRegEmail = regEmail.text.toString()
        val inputRegPassword = regPassword.text.toString()

        auth.createUserWithEmailAndPassword(inputRegEmail, inputRegPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val Intent = Intent(this, Dashboard::class.java)
                    startActivity(Intent)

                    val currentUser = Firebase.auth.currentUser
                    currentUser?.let{
                        val uid = currentUser.uid
                        Toast.makeText(this, uid, Toast.LENGTH_LONG,).show()
                        //Add
                        databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseName")
                        var databaseClass = DatabaseClass(uid, userName, inputRegEmail)
                        var dataKey = databaseReference.push().getKey();
                        databaseReference.child("users").child(uid).setValue(databaseClass).addOnSuccessListener()
                        {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        }
                    }

                    Toast.makeText(baseContext,"Authentication success.", Toast.LENGTH_SHORT,).show()

                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(baseContext,"Authentication failed.", Toast.LENGTH_SHORT,).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Error occured ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }



    }
}
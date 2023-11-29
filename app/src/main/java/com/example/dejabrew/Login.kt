package com.example.dejabrew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val loginButton = findViewById<Button>(R.id.Loginbtn)
        loginButton.setOnClickListener {
//            val Intent = Intent(this, Dashboard::class.java)
//            startActivity(Intent)
            performLogin()
        }

    }

    private fun performLogin(){
        //declaration of variables
        val loginEmail: EditText = findViewById(R.id.loginEmail)
        val loginPassword: EditText = findViewById(R.id.loginPassword)

        //validation if empty
        if (loginEmail.text.isEmpty() || loginPassword.text.isEmpty()){
            Toast.makeText(this, "Please fill out all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        val inputLoginEmail = loginEmail.text.toString()
        val inputLoginPassword = loginPassword.text.toString()

        //login code
        auth.signInWithEmailAndPassword(inputLoginEmail, inputLoginPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val Intent = Intent(this, Dashboard::class.java)
                    startActivity(Intent)

                    Toast.makeText(baseContext,"Authentication success.", Toast.LENGTH_SHORT,).show()
                    val currentUser = Firebase.auth.currentUser
                    currentUser?.let{
                        val uid = currentUser.uid
                        Toast.makeText(this, uid, Toast.LENGTH_LONG,).show()

                    }
                } else {
                    Toast.makeText(baseContext, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Error occured ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }

    }

    }

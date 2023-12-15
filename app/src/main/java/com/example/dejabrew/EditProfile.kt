package com.example.dejabrew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfile : AppCompatActivity() {

    lateinit var databaseReference : DatabaseReference
    lateinit var uid : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val backButton = findViewById<Button>(R.id.edtprofileBackbtn)
        backButton.setOnClickListener {
            val Intent = Intent(this, Dashboard::class.java)
            startActivity(Intent)
        }
//edtProfileSave
        val EPSave = findViewById<Button>(R.id.edtProfileSave)
        EPSave.setOnClickListener {
            performEdit()
        }
//delete
        val EPDelete = findViewById<Button>(R.id.edtProfileDelete)
        EPDelete.setOnClickListener {
            performDelete()
        }
        //current user
        val currentUser = Firebase.auth.currentUser
        currentUser?.let{
            uid = currentUser.uid
            Toast.makeText(this, uid, Toast.LENGTH_LONG,).show()

        }
        val edtEmail = findViewById<EditText>(R.id.EditProfileEmailAddress)
        val editUserName = findViewById<EditText>(R.id.edtUserName)
        databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseName")
        databaseReference.child("users").child(uid).child("email").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val email2 = dataSnapshot.getValue(String::class.java)
                edtEmail.setText(email2)
                // Update your UI here with the retrieved empName
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("MainActivity", "Failed to read value.", databaseError.toException())
            }
        })
        databaseReference.child("users").child(uid).child("username").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val username2 = dataSnapshot.getValue(String::class.java)
                editUserName.setText(username2)
                // Update your UI here with the retrieved empName
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("MainActivity", "Failed to read value.", databaseError.toException())
            }
        })
    }
    private fun performEdit() {
        //DECLARATIONS OF VARIABLES
        val editUserName = findViewById<EditText>(R.id.edtUserName)
        val edtEmail = findViewById<EditText>(R.id.EditProfileEmailAddress)


//        if (edtEmail.text.isEmpty() || edtPassword.text.isEmpty()){
//            Toast.makeText(this, "Kindly fill out all fields", Toast.LENGTH_SHORT).show()
//            return
//        }
        val userName =  editUserName.text.toString()
        val inputRegEmail = edtEmail.text.toString()


        //Update
        val currentUser = Firebase.auth.currentUser
        currentUser?.let {
            val uid = currentUser.uid
            var userUpdate = mapOf<String, String>("username" to userName)
            var emailUpdate = mapOf<String, String>("email" to inputRegEmail)
            //var databaseClass = DatabaseClass(uid, userName, inputRegEmail)
            //USERNAME
            databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseName")
            databaseReference.child("users").child(uid)
                .updateChildren(userUpdate).addOnSuccessListener{
                    Toast.makeText(this,"Success - Update",Toast.LENGTH_SHORT).show()
                }
            //EMAIL
            databaseReference.child("users").child(uid)
                .updateChildren(emailUpdate).addOnSuccessListener{
                    Toast.makeText(this,"Success - Update",Toast.LENGTH_SHORT).show()
                }
        }
    }
    private fun performDelete() {
        databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseName")
        databaseReference.child("users").child(uid).removeValue().addOnSuccessListener {
            val Intent = Intent(this, Login::class.java)
            startActivity(Intent)
            Toast.makeText(this,"Success - Delete",Toast.LENGTH_SHORT).show()

        }

    }

}
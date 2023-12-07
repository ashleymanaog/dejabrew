package com.example.dejabrew

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID
import kotlin.random.Random

class Upload : AppCompatActivity() {
    // BASTA FOR UPLOAD PICTURE
    private var fileUri: Uri? = null
    lateinit var chooseImageBtn: Button

    //lateinit var uploadImageBtn: Button
    lateinit var imageView: ImageView
    private lateinit var photoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)


        val backButton = findViewById<Button>(R.id.uploadBackbtn)
        backButton.setOnClickListener {
            val Intent = Intent(this, Dashboard::class.java)
            startActivity(Intent)
        }

        val buttonRegister: Button = findViewById(R.id.uploadbtn)

        buttonRegister.setOnClickListener {
            uploadImage()
            performUpload()
        }
// UPLOAD PHOTO
        var fileUri: Uri? = null;
        // on below line initializing variables for buttons and image view.
        chooseImageBtn = findViewById(R.id.idBtnChooseImage)
        //uploadImageBtn = findViewById(R.id.idBtnUploadImage)
        imageView = findViewById(R.id.idIVImage)

        // on below line adding click listener for our choose image button.
        chooseImageBtn.setOnClickListener {
            // on below line calling intent to get our image from phone storage.
            val intent = Intent()
            // on below line setting type of files which we want to pick in our case we are picking images.
            intent.type = "image/*"
            // on below line we are setting action to get content
            intent.action = Intent.ACTION_GET_CONTENT
            // on below line calling start activity for result to choose image.
            startActivityForResult(
                // on below line creating chooser to choose image.
                Intent.createChooser(
                    intent,
                    "Pick your image to upload"
                ),
                22
            )
        }

    }


    //UPLOAD PHOTO

    // on below line adding on activity result method this method is called when user picks the image.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // on below line we are checking if the result is ok
        if (requestCode == 22 && resultCode == RESULT_OK && data != null && data.data != null) {
            // on below line initializing file uri with the data which we get from intent
            fileUri = data.data
            try {
                // on below line getting bitmap for image from file uri.
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri);
                // on below line setting bitmap for our image view.
                imageView.setImageBitmap(bitmap)

                // Store the name of the photo in a variable
                photoName = fileUri?.lastPathSegment ?: ""

            } catch (e: Exception) {
                // handling exception on below line
                e.printStackTrace()
            }
        }
    }

    // on below line creating a function to upload our image.
    fun uploadImage() {
        // on below line checking weather our file uri is null or not.
        if (fileUri != null) {
            // on below line displaying a progress dialog when uploading an image.
            val progressDialog = ProgressDialog(this)
            // on below line setting title and message for our progress dialog and displaying our progress dialog.
            progressDialog.setTitle("Uploading...")
            progressDialog.setMessage("Uploading your image..")
            progressDialog.show()

            // on below line creating a storage refrence for firebase storage and creating a child in it with
            // random uuid.
            photoName = UUID.randomUUID().toString();
            val ref: StorageReference = FirebaseStorage.getInstance().getReference()
                .child(photoName)

            // on below line adding a file to our storage.
            ref.putFile(fileUri!!).addOnSuccessListener {
                // this method is called when file is uploaded.
                // in this case we are dismissing our progress dialog and displaying a toast message
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Image Uploaded..", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                // this method is called when there is failure in file upload.
                // in this case we are dismissing the dialog and displaying toast message
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Fail to Upload Image..", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    //UPLOAD TEXT
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
// Check if the inputs are empty
        if (title_upload2.trim().isEmpty() || recipe_upload2.trim().isEmpty()) {
            // If the inputs are empty, show a toast message
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
        } else {

            databaseReference = FirebaseDatabase.getInstance().getReference("DatabaseName")
            var databaseClass = UploadClass(id, title_upload2, recipe_upload2, photoName)
            var dataKey = databaseReference.push().getKey();
            databaseReference.child("Recipe").child(id).setValue(databaseClass)
                .addOnSuccessListener()
                {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
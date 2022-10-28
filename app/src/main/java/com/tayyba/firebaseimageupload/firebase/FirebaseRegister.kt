package com.tayyba.firebaseimageupload.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.tayyba.firebaseimageupload.R

class FirebaseRegister : AppCompatActivity() {

   private val firebaseFirstore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_register)


        // to listen for events in a collection
        firebaseFirstore.collection("users")
            .addSnapshotListener { value, error ->
                value?.documents?.let { documentSnapshots ->
                    for (snapshot in documentSnapshots) {
                        //snapshot.toObject(User::class)
                    }
                }
            }

        firebaseFirstore.collection("users")
            .get()
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
            .addOnCompleteListener {

            }

        firebaseFirstore.collection("users")
            .whereEqualTo("email", "abc@gmail.com")
            .get()
            .addOnSuccessListener {

            }


    }
}
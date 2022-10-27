package com.tayyba.firebaseimageupload.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.tayyba.firebaseimageupload.R

class FirebaseRegister : AppCompatActivity() {

    val firebaseFirstore = FirebaseFirestore.getInstance()
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
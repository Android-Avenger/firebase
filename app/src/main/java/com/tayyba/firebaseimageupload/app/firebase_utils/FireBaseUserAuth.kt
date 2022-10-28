package com.tayyba.firebaseimageupload.app.firebase_utils

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.tayyba.firebaseimageupload.app.models.User

class FireBaseUserAuth(
   private val firebaseAuth : FirebaseFirestore
) {


    fun registerUser(name : String , email : String , password : String){
        firebaseAuth.collection("user")
            .add(User(name,email,password))
            .addOnSuccessListener {
                Log.d("FireBaseAuth","Successful")
            }
            .addOnFailureListener {
                Log.d("FireBaseAuth","Failed")

            }
    }

    fun loginUser(email : String , password : String){
        firebaseAuth.collection("user")
            .whereEqualTo("email",email)
            .whereEqualTo("password",password)
            .get()
            .addOnSuccessListener {
                Log.d("Login","ok")

            }
            .addOnFailureListener {
                Log.d("Login","not ok")

            }
    }
}
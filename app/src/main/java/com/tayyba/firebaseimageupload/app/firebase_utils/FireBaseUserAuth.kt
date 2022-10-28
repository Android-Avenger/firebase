package com.tayyba.firebaseimageupload.app.firebase_utils

import com.google.firebase.firestore.FirebaseFirestore
import com.tayyba.firebaseimageupload.app.models.User

class FireBaseUserAuth(
   private val firebaseAuth : FirebaseFirestore
) {

    fun registerUser(name : String , email : String , password : String){
        firebaseAuth.collection("user")
            .add(User(name,email,password))
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }

    fun loginUser(email : String , password : String){
        firebaseAuth.collection("user")
            .whereEqualTo("email",email)
            .whereEqualTo("password",password)
            .get()
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }
}
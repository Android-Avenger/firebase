package com.tayyba.firebaseimageupload.app.firebase_utils

import android.app.ProgressDialog
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.webkit.MimeTypeMap
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tayyba.firebaseimageupload.app.models.User
import java.io.File

class FireBaseUserAuth(
    private val firebaseAuth: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
) {
    private val handler = Handler()

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    ) {
        firebaseAuth.collection("user")
            .add(User(name, email, password))
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
               onError(it.message)
            }
    }

    fun loginUser(
        email: String, password: String,
        onError: (String?) -> Unit, onSuccess: () -> Unit
    ) {
        firebaseAuth.collection("user")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it.message)
            }
    }

    fun uploadVideo(videoUri : Uri,context: Context){

        firebaseStorage.getReference("Videos/" + System.currentTimeMillis() + "." + getFileType(context,videoUri))
            .putFile(videoUri)
            .addOnSuccessListener {
                Toast.makeText(context,"SuccessFully uploaded",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(context,"SuccessFully uploaded",Toast.LENGTH_LONG).show()
            }
            .addOnProgressListener { taskSnapShot ->
                showProgressDialogHorizontal(context,
                taskSnapShot.totalByteCount.toInt()/1000,
                taskSnapShot.bytesTransferred.toInt()/1000)

            }

    }
    private fun getFileType(context: Context, uri: Uri): String? {
        val extension: String? = if (uri.scheme.equals(ContentResolver.SCHEME_CONTENT)) {
            val mime = MimeTypeMap.getSingleton()
            mime.getExtensionFromMimeType(context.contentResolver.getType(uri))
        } else {
            MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(uri.path?.let { File(it) }).toString())
        }
        return extension
    }

    private fun showProgressDialogHorizontal(context: Context,max: Int = 100, progress: Int = 0) {

        var progressStatus = progress
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Uploading")
        progressDialog.setMessage("Image is uploading ")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog.setCancelable(true)
        progressDialog.max = max
        progressDialog.show()

        Thread {
            while (progressStatus < max) {
                try {
                    Thread.sleep(200)
                    progressStatus += 20
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                handler.post { progressDialog.progress = progressStatus }
            }
            progressDialog.dismiss()
        }.start()
    }


}
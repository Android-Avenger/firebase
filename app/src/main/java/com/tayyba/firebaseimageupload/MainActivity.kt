package com.tayyba.firebaseimageupload

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.google.firebase.storage.FirebaseStorage
import com.skydoves.landscapist.glide.GlideImage
import com.tayyba.firebaseimageupload.ui.theme.FireBaseImageUploadTheme
import java.util.*


class MainActivity : ComponentActivity() {

    var img = Uri.Builder().build()
    val mViewModel: ImagePickerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uri by mViewModel.img.observeAsState()
            FireBaseImageUploadTheme {
                Surface(
                    Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        GlideImage(
                            imageModel = uri,
                            contentScale = ContentScale.Fit,
                            placeHolder = ImageBitmap.imageResource(R.drawable.image),
                            modifier = Modifier.size(400.dp)
                        )
                        Spacer(modifier = Modifier.size(20.dp))

                        Button(
                            modifier = Modifier
                                .width(100.dp)
                                .height(40.dp), onClick = {
                                openGalleryForImage()
                            }) {
                            Text(text = "Upload")
                        }
                    }
                }
            }
        }
    }

    val REQUEST_CODE = 100
    private fun openGalleryForImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Image from here..."
            ),
            22
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 22) {
            mViewModel.img.value = data?.data
            data?.data?.let { uploadImage(it) }
        }
    }


    private fun uploadImage(filePath: Uri) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()
        val ref =
            FirebaseStorage.getInstance().reference.child("images/" + UUID.randomUUID().toString())
        ref.putFile(filePath)
            .addOnSuccessListener { progressDialog.dismiss() }
            .addOnFailureListener { progressDialog.dismiss() }
            .addOnProgressListener { taskSnapshot ->
                val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                    .totalByteCount
                progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
            }
    }

}
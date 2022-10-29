package com.tayyba.firebaseimageupload.app.fragments.dashboard_fragment

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.tayyba.firebaseimageupload.app.firebase_utils.FireBaseUserAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardFragmentViewModel @Inject constructor(
    private val fireBaseUserAuth: FireBaseUserAuth
) :ViewModel(){

     fun uploadImage(context: Context,videoUri: Uri){
         fireBaseUserAuth.uploadVideo(videoUri, context)
     }

}
package com.tayyba.firebaseimageupload

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImagePickerViewModel : ViewModel() {

    val img = MutableLiveData<Uri>()

    fun pick():(img : Uri)->Unit = {
        img.value = it
    }


}
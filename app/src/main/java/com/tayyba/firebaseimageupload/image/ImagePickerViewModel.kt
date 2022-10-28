package com.tayyba.firebaseimageupload.image

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImagePickerViewModel : ViewModel() {

    val img = MutableLiveData<Uri>()

    fun pick():(img : Uri)->Unit = {
        img.value = it
    }

    var downloadedPercentage = MutableLiveData<Float>()

    fun startThreadGradient() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {

                val totalDownloadSize = 1024f
                var downloadedSize = 0f

                while (true) {

                    downloadedSize += ((1..100).random().toFloat())

                    if (downloadedSize < totalDownloadSize) {
                        withContext(Dispatchers.Main) {
                            downloadedPercentage.value =
                                ((downloadedSize / totalDownloadSize) * 100)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            downloadedPercentage.value = 100f
                        }
                        break
                    }

                    delay(1000)
                }

            }
        }
    }


}
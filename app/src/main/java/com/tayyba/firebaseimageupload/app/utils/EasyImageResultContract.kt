package com.tayyba.firebaseimageupload.app.utils

import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource

data class EasyImageResultContract(
    val onCancelled: (MediaSource) -> Unit = {},
    val onError: (Throwable, MediaSource) -> Unit = {_, _ -> },
    val onSuccess: (Array<MediaFile>, MediaSource) -> Unit,
)
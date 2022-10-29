package com.tayyba.firebaseimageupload.app.utils

import android.Manifest
import android.content.Context
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class FileStoragePermissionChecker(
    private val context: Context,
    private val onAnyPermissionPermanentlyDenied: () -> Unit,
    private val onPermissionsDenial: () -> Unit,
    private val onPermissionsGranted: () -> Unit
): MultiplePermissionsListener {

    fun check() {
        Dexter
            .withContext(context)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(this)
            .check()
    }

    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
        report?.let {
            if (it.isAnyPermissionPermanentlyDenied) {
                onAnyPermissionPermanentlyDenied()
            } else if (!it.areAllPermissionsGranted()) {
                onPermissionsDenial()
            } else {
                onPermissionsGranted()
            }
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        request: MutableList<PermissionRequest>?,
        token: PermissionToken?
    ) {
        token?.continuePermissionRequest()
    }
}
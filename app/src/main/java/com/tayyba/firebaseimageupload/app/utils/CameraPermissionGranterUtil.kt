package com.tayyba.firebaseimageupload.app.utils

import android.Manifest
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class CameraPermissionGranterUtil(
    private val activity: ComponentActivity,
    private val onAllPermissionGranted: () -> Unit,
): MultiplePermissionsListener {

    fun check() {
        Dexter.withContext(activity).withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(this).onSameThread().check()
    }

    private fun shortToast(toast: String) {
        Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show()
    }

    private fun longToast(toast: String) {
        Toast.makeText(activity, toast, Toast.LENGTH_LONG).show()
    }

    override fun onPermissionsChecked(permissionsReport: MultiplePermissionsReport?) {
        permissionsReport?.let { report ->
            when {
                report.areAllPermissionsGranted() -> {
                    onAllPermissionGranted()
                }
                else -> {
                    report.deniedPermissionResponses.forEach { deniedResponse ->
                        if (deniedResponse.permissionName == Manifest.permission.CAMERA) {
                            when {
                                deniedResponse.isPermanentlyDenied -> {
                                    longToast("You have permanently denied camera permission, please grant from app settings")
                                }
                                else -> shortToast("Camera permission is required to launch camera.")
                            }
                        } else if (deniedResponse.permissionName == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                            when {
                                deniedResponse.isPermanentlyDenied -> {
                                    longToast("You have permanently denied storage permission, please grant from app settings")
                                }
                                else -> shortToast("Storage permission is required to save captured image.")
                            }
                        }
                    }
                }
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
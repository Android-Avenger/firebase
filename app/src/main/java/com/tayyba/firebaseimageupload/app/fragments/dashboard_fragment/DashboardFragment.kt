package com.tayyba.firebaseimageupload.app.fragments.dashboard_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tayyba.firebaseimageupload.app.fragments.dashboard_fragment.layout.DashboardFragmentLayout
import com.tayyba.firebaseimageupload.app.utils.EasyImageResultContract
import com.tayyba.firebaseimageupload.app.utils.FileStoragePermissionChecker
import dagger.hilt.android.AndroidEntryPoint
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment : Fragment() {

   private val mViewModel:DashBoardFragmentViewModel by viewModels()
    @Inject
    lateinit var mEasyImage: EasyImage
    var mEasyImageResultContract: EasyImageResultContract? = null

    private val fileChooserContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                mViewModel.uploadImage(
                    requireContext(),
                    it
                )
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            DashboardFragmentLayout {
                FileStoragePermissionChecker(
                    context = requireContext(),
                    onAnyPermissionPermanentlyDenied = {
                        Toast.makeText(
                            requireContext(),
                            "Grant Permissions from app settings!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onPermissionsDenial = {
                        Toast.makeText(
                            requireContext(),
                            "Permission is required to select outline!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onPermissionsGranted = {
                        fileChooserContract.launch("*/*")
                    }
                ).check()

            }
        }

    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mEasyImage.handleActivityResult(
            requestCode,
            resultCode,
            data,
            requireActivity(),
            object : EasyImage.Callbacks {
                override fun onCanceled(source: MediaSource) {
                    mEasyImageResultContract?.let {
                        it.onCancelled(source)
                    }
                }

                override fun onImagePickerError(error: Throwable, source: MediaSource) {
                    mEasyImageResultContract?.let {
                        it.onError(error, source)
                    }
                }

                override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                    mEasyImageResultContract?.let {
                        it.onSuccess(imageFiles, source)
                    }
                }
            })
    }
}
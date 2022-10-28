package com.tayyba.firebaseimageupload.image

import android.animation.ObjectAnimator
import android.app.ProgressDialog
import android.os.Build
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlin.coroutines.coroutineContext

class HorizontalProgress {
    var progressDialog: ProgressDialog? = null
    private var progressStatus = 0
    private val handler = Handler()
    fun showProgressDialogHorizontal() {
        progressDialog = ProgressDialog(progressDialog!!.context)
        progressDialog!!.setTitle("Please Wait..")
        progressDialog!!.setMessage("Downloading Video ...")
        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog!!.setCancelable(false)
        progressDialog!!.max = 100
        progressDialog!!.show()

        // Start lengthy operation in a background thread
        Thread {
            while (progressStatus < 100) {
                try {
                    // Here I'm making thread sleep to show progress
                    Thread.sleep(200)
                    progressStatus += 5
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                // Update the progress bar
                handler.post { progressDialog!!.progress = progressStatus }
            }
            progressDialog!!.dismiss()
        }.start()
    }

//    fun progressBar() {
//
//        val layout = RelativeLayout(coroutineContext)
//        val progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal)
////        progressBar.isIndeterminate = true
//        progressBar.visibility = View.VISIBLE
////        progressBar.progress = 600
//        progressBar.max = 1000
//        progressBar.setProgress(600, true)
//        ObjectAnimator.ofInt(progressBar, "progress", 600)
//            .setDuration(3000)
//            .start()
//        val params = RelativeLayout.LayoutParams(600, 100)
//        params.addRule(RelativeLayout.CENTER_IN_PARENT)
//        layout.addView(progressBar, params)
//
//        setContentView(layout)
//
//
//    }
//
//    fun showProgressDialogue() {
//
//        val progressBar = ProgressDialog(this)
//        val inflater = (this).layoutInflater
//        val view = inflater.inflate(R.layout.progressbar, null)
//        view.findViewById<TextView>(R.id.tv).text = "Uploading"
//        progressBar.setMessage("test")
//        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
//        progressBar.setContentView(view)
//        progressBar.setCancelable(false)
//        progressBar.setCanceledOnTouchOutside(false)
//        progressBar.show()
//
//    }
//
}
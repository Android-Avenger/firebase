package com.tayyba.firebaseimageupload.app.fragments.login_fragment.middlewares

import android.annotation.SuppressLint
import com.tayyba.firebaseimageupload.app.fragments.login_fragment.LoginFragment
import com.tayyba.firebaseimageupload.app.fragments.login_fragment.state.LoginFragmentState
import es.dmoral.toasty.Toasty

@SuppressLint("CheckResult")
fun LoginFragment.registerFragmentValidator(
    state: LoginFragmentState,
    onSuccess: () -> Unit
) {
    val context = requireContext()
    when {
        state.name.trim().isEmpty() -> {
            Toasty.error(context, "Enter Name")
        }
        state.email.trim().isEmpty() -> {
            Toasty.error(context, "Enter Email")
        }
        state.password.trim().isEmpty() -> {
            Toasty.error(context, "Enter Password")
        }
        else->{
            onSuccess()
        }
    }
}
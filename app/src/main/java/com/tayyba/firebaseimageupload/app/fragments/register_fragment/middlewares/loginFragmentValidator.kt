package com.tayyba.firebaseimageupload.app.fragments.register_fragment.middlewares

import android.annotation.SuppressLint
import com.tayyba.firebaseimageupload.app.fragments.register_fragment.RegisterFragment
import com.tayyba.firebaseimageupload.app.fragments.register_fragment.state.RegisterFragmentState
import es.dmoral.toasty.Toasty

@SuppressLint("CheckResult")
fun RegisterFragment.registerFragmentValidator(
    state: RegisterFragmentState,
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
        state.confirmPassword.trim().isEmpty() -> {
            Toasty.error(context, "Enter Password Again")
        }
        state.password.trim().isEmpty() != state.confirmPassword.trim().isEmpty() -> {
            Toasty.error(context, "Password did not match ")
        }
        else->{
            onSuccess()
        }
    }
}
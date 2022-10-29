package com.tayyba.firebaseimageupload.app.fragments.login_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tayyba.firebaseimageupload.app.firebase_utils.FireBaseUserAuth
import com.tayyba.firebaseimageupload.app.fragments.login_fragment.state.LoginFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val mFireBaseAuth: FireBaseUserAuth
) : ViewModel() {

    val state = LoginFragmentState()
    val trigger = MutableLiveData(1)


    private fun triggerState() {
        trigger.value = trigger.value!!.plus(1)
    }


    fun loginUser(
        onError: (String?) -> Unit,
        onSuccess: () -> Unit
    ) {
        mFireBaseAuth.loginUser(
            email = state.email,
            password = state.password,
            onError = {
                onError(it)
            },
            onSuccess = onSuccess
        )
    }

    fun nameController(): (String) -> Unit = {
        state.name = it
        triggerState()
    }

    fun emailController(): (String) -> Unit = {
        state.email = it
        triggerState()
    }

    fun passwordController(): (String) -> Unit = {
        state.password = it
        triggerState()
    }


}
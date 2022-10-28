package com.tayyba.firebaseimageupload.app.fragments.register_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tayyba.firebaseimageupload.app.firebase_utils.FireBaseUserAuth
import com.tayyba.firebaseimageupload.app.fragments.register_fragment.state.RegisterFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val mFireBaseAuth : FireBaseUserAuth
) : ViewModel() {

    val state = RegisterFragmentState()
    val trigger = MutableLiveData(1)



    private fun triggerState() {
        trigger.value = trigger.value!!.plus(1)
    }


    fun registerUser() {
        mFireBaseAuth.registerUser(state.name,state.email,state.password)
    }



//    fun registerUser(
//        state: RegisterFragmentState,
//        onSuccess: () -> Unit,
//        onError: () -> Unit
//    ) {
//        firebaseFireStoreAuth?.let {
//            it.collection("user")
//                .add(User(state.name, state.email, state.password))
//                .addOnSuccessListener { onSuccess() }
//                .addOnFailureListener { onError() }
//        }
//    }

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

    fun confirmPassword(): (String) -> Unit = {
        state.confirmPassword = it
        triggerState()
    }

}
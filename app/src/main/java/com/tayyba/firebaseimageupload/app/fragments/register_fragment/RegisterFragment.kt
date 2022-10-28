package com.tayyba.firebaseimageupload.app.fragments.register_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tayyba.firebaseimageupload.app.fragments.register_fragment.layout.RegisterFragmentLayout
import com.tayyba.firebaseimageupload.app.fragments.register_fragment.middlewares.registerFragmentValidator
import com.tayyba.firebaseimageupload.app.fragments.register_fragment.state.RegisterFragmentState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val mViewModel: RegisterFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val trigger by mViewModel.trigger.observeAsState()
            val state = RegisterFragmentState()
            trigger?.let {
                RegisterFragmentLayout(
                    state = state,
                    onNameChanged = mViewModel.nameController(),
                    onEmailChanged = mViewModel.emailController(),
                    onPasswordChanged = mViewModel.passwordController(),
                    onConfirmPasswordChanged = mViewModel.confirmPassword(),
                    onRegisterButtonClick = {
                        registerFragmentValidator(
                            state = state,
                            onSuccess = { mViewModel.registerUser() }
                        )
                    }
                )
            }
        }
    }
}
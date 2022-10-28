package com.tayyba.firebaseimageupload.app.fragments.login_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.tayyba.firebaseimageupload.R
import com.tayyba.firebaseimageupload.app.fragments.login_fragment.layout.LoginFragmentLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val mViewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val trigger by mViewModel.trigger.observeAsState()
            trigger?.let {
                LoginFragmentLayout(
                    state = mViewModel.state,
                    onNameChanged = mViewModel.nameController(),
                    onEmailChanged = mViewModel.emailController(),
                    onPasswordChanged = mViewModel.passwordController(),
                    onLoginButtonClick = {mViewModel.loginUser()},
                    onRegisterButtonClick = {
                        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)                    }
                )
            }
        }
    }
}
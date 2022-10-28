package com.tayyba.firebaseimageupload.app.fragments.register_fragment.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tayyba.firebaseimageupload.app.composables.MyAuthButton
import com.tayyba.firebaseimageupload.app.composables.MyOutlineTextField
import com.tayyba.firebaseimageupload.app.fragments.register_fragment.state.RegisterFragmentState
import com.tayyba.firebaseimageupload.ui.theme.Reeem

@Composable
fun RegisterFragmentLayout(
    state: RegisterFragmentState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onRegisterButtonClick: () -> Unit
) {
    Column(
        Modifier
            .background(Color.White)
            .padding(30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "REGISTER",
            fontSize = 30.sp,
            fontFamily = Reeem,
            fontWeight = FontWeight.Bold
        )
        MyOutlineTextField(label = "Name", value = state.name, onValueChange = onNameChanged)
        MyOutlineTextField(label = "Email", value = state.email, onValueChange = onEmailChanged)
        MyOutlineTextField(
            label = "Password",
            value = state.password,
            onValueChange = onPasswordChanged
        )
        MyOutlineTextField(
            label = "Confirm Password",
            value = state.confirmPassword,
            onValueChange = onConfirmPasswordChanged
        )
        MyAuthButton(label = "Register")
    }

}
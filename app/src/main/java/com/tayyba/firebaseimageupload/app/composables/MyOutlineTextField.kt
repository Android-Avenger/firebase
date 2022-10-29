package com.tayyba.firebaseimageupload.app.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tayyba.firebaseimageupload.ui.theme.DarkBlue
import com.tayyba.firebaseimageupload.ui.theme.DarkBlueLite
import com.tayyba.firebaseimageupload.ui.theme.QuickSand

@Composable
fun MyOutlineTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textStyle = TextStyle(
                color = DarkBlue,
                fontFamily = QuickSand,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            ),
            value = value,
            enabled = true,
            onValueChange = onValueChange,
            label = {
                Text(
                    label, style = TextStyle(
                        fontFamily = QuickSand,
                    )
                )
            },
            keyboardOptions = keyboardOptions,
            shape = RoundedCornerShape(30),
            visualTransformation = visualTransformation,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = DarkBlue,
                unfocusedBorderColor = DarkBlue,
                backgroundColor = Color.White,
                focusedLabelColor = DarkBlue,
                unfocusedLabelColor = DarkBlueLite,
                cursorColor = DarkBlue
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))

    }
}
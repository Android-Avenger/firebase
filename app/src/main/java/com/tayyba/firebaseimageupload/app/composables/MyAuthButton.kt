package com.tayyba.firebaseimageupload.app.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tayyba.firebaseimageupload.ui.theme.DarkBlue

@Composable
fun MyAuthButton(label: String = "", onClick: () -> Unit = {}) {
    Spacer(modifier = Modifier.padding(10.dp))
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(120.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkBlue,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold

            )
        )

    }
    Spacer(modifier = Modifier.padding(10.dp))

}


package com.tayyba.firebaseimageupload.app.fragments.dashboard_fragment.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tayyba.firebaseimageupload.R

@Composable
fun DashboardFragmentLayout(
    onAddVideoBtnClick:()->Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(35.dp),
    ) {
        Column(
            Modifier
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        }
        Image(
            painter = painterResource(id = R.drawable.add_video),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.BottomEnd).clickable {
                   onAddVideoBtnClick()
                },
        )

    }

}
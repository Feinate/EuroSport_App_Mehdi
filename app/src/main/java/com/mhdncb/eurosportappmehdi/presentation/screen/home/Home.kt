package com.mhdncb.eurosportappmehdi.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdncb.eurosportappmehdi.R

@Composable
fun Home(
    onItemClick: (Int) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        HeaderBar()
    }

}

@Composable
private fun HeaderBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(color = Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(modifier = Modifier.padding(vertical = 12.dp), text = "salut lt", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}
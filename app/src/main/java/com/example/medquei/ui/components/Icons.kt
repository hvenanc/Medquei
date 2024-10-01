package com.example.medquei.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.medquei.R

@Composable
fun IconImage() {
    Image(
        painter = painterResource(id = R.drawable.pill),
        contentDescription = "Descrição do ícone",
        modifier = Modifier.size(24.dp),
        contentScale = ContentScale.Fit
    )
}
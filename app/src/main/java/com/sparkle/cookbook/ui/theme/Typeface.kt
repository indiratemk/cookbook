package com.sparkle.cookbook.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Title(text: String) {
    Text(
        text = text,
        style = Typography().h4,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun Subtitle(text: String) {
    Text(
        text = text,
        style = Typography().h6,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            start = 16.dp,
            top = 8.dp,
            end = 16.dp,
            bottom = 16.dp
        )
    )
}

@Composable
fun Body(text: String) {
    Text(
        text = text,
        style = Typography().body2,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 8.dp)
    )
}

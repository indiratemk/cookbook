package com.sparkle.cookbook.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
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

@Composable
fun SingleLineInput(
    input: MutableState<TextFieldValue>,
    hint: String,
    topSpace: Dp = 0.dp,
    bottomSpace: Dp = 0.dp,
    startSpace: Dp = 0.dp,
    endSpace: Dp = 0.dp,
) {
    TextField(
        value = input.value,
        onValueChange = { input.value = it },
        placeholder = {
            Text(
                text = hint
            )
        },
        modifier = Modifier
            .padding(
                start = startSpace,
                end = endSpace,
                top = topSpace,
                bottom = bottomSpace
            ).fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Green,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = Shapes.small,
        singleLine = true
    )
}

@Composable
fun MultiLineInput(
    input: MutableState<TextFieldValue>,
    hint: String,
    topSpace: Dp = 0.dp,
    bottomSpace: Dp = 0.dp,
    startSpace: Dp = 0.dp,
    endSpace: Dp = 0.dp,
) {
    TextField(
        value = input.value,
        onValueChange = { input.value = it },
        placeholder = {
            Text(
                text = hint
            )
        },
        modifier = Modifier
            .padding(
                start = startSpace,
                end = endSpace,
                top = topSpace,
                bottom = bottomSpace
            ).fillMaxWidth()
            .height(120.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Green,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = Shapes.small,
        maxLines = 5,
    )
}

@Composable
fun DefaultButton(
    title: String,
    topSpace: Dp = 0.dp,
    bottomSpace: Dp = 0.dp,
    startSpace: Dp = 0.dp,
    endSpace: Dp = 0.dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(
                start = startSpace,
                end = endSpace,
                top = topSpace,
                bottom = bottomSpace
            ).fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Green,
            contentColor = Color.White
        ),
        shape = Shapes.small
    ) {
        Text(text = title)
    }
}

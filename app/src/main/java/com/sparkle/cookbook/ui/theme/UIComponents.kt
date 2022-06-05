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

// TEXTS
@Composable
fun Title(
    text: String,
    indents: Dp = 0.dp
) {
    Text(
        text = text,
        style = Typography().h4,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(indents)
    )
}

@Composable
fun Subtitle(
    text: String,
    startIndent: Dp = 0.dp,
    endIndent: Dp = 0.dp,
    topIndent: Dp = 0.dp,
    bottomIndent: Dp = 0.dp,
) {
    Text(
        text = text,
        style = Typography().h6,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            start = startIndent,
            top = topIndent,
            end = endIndent,
            bottom = bottomIndent
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

// TEXT FIELDS
@Composable
fun SingleLineInput(
    input: MutableState<TextFieldValue>,
    hint: String,
    modifier: Modifier
) {
    TextField(
        value = input.value,
        onValueChange = { input.value = it },
        placeholder = {
            Text(
                text = hint
            )
        },
        modifier = modifier,
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
    modifier: Modifier
) {
    TextField(
        value = input.value,
        onValueChange = { input.value = it },
        placeholder = {
            Text(
                text = hint
            )
        },
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Green,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = Shapes.small,
        maxLines = 5,
    )
}

// BUTTONS
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

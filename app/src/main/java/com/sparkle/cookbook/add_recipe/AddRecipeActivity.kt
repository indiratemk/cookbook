package com.sparkle.cookbook.add_recipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sparkle.cookbook.R
import com.sparkle.cookbook.ui.theme.CookBookTheme
import com.sparkle.cookbook.ui.theme.DefaultButton
import com.sparkle.cookbook.ui.theme.MultiLineInput
import com.sparkle.cookbook.ui.theme.SingleLineInput
import com.sparkle.cookbook.ui.theme.Subtitle
import com.sparkle.cookbook.ui.theme.Title

class AddRecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                AddRecipeScreen()
            }
        }
    }
}

@Composable
fun AddRecipeScreen() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxSize(),
        content = {
            Title(
                text = LocalContext.current.getString(R.string.add_recipe_title),
                indents = 16.dp
            )
            Subtitle(
                text = LocalContext.current.getString(R.string.add_recipe_name),
                startIndent = 16.dp,
                topIndent = 8.dp,
                endIndent = 16.dp,
                bottomIndent = 16.dp,
            )

            val titleInput = remember { mutableStateOf(TextFieldValue()) }
            SingleLineInput(
                input = titleInput,
                hint = LocalContext.current.getString(R.string.add_recipe_name_hint),
                startSpace = 16.dp,
                bottomSpace = 16.dp,
                endSpace = 16.dp
            )
            Subtitle(
                text = LocalContext.current.getString(R.string.add_recipe_description),
                startIndent = 16.dp,
                topIndent = 8.dp,
                endIndent = 16.dp,
                bottomIndent = 16.dp,
            )

            val descriptionInput = remember { mutableStateOf(TextFieldValue()) }
            MultiLineInput(
                input = descriptionInput,
                hint = LocalContext.current.getString(R.string.add_recipe_description_hint),
                startSpace = 16.dp,
                bottomSpace = 16.dp,
                endSpace = 16.dp
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter,
                content = {
                    DefaultButton(
                        title = LocalContext.current.getString(R.string.add_recipe_create_button),
                        startSpace = 16.dp,
                        endSpace = 16.dp,
                        topSpace = 8.dp
                    ) {}
                }
            )
        }
    )
}

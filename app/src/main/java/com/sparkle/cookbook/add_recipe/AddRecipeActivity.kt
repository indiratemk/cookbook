package com.sparkle.cookbook.add_recipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    LazyColumn(content = {
        item { Title(text = LocalContext.current.getString(R.string.add_recipe_title)) }
        item { Subtitle(text = LocalContext.current.getString(R.string.add_recipe_name)) }
        item {
            val titleInput = remember { mutableStateOf(TextFieldValue()) }
            SingleLineInput(
                input = titleInput,
                hint = LocalContext.current.getString(R.string.add_recipe_name_hint),
                startSpace = 16.dp,
                bottomSpace = 16.dp,
                endSpace = 16.dp
            )
        }
        item { Subtitle(text = LocalContext.current.getString(R.string.add_recipe_description)) }
        item {
            val descriptionInput = remember { mutableStateOf(TextFieldValue()) }
            MultiLineInput(
                input = descriptionInput,
                hint = LocalContext.current.getString(R.string.add_recipe_description_hint),
                startSpace = 16.dp,
                bottomSpace = 16.dp,
                endSpace = 16.dp
            )
        }
        item {
            DefaultButton(
                title = LocalContext.current.getString(R.string.add_recipe_create_button),
                startSpace = 16.dp,
                endSpace = 16.dp,
                topSpace = 8.dp
            ) {}
        }
    })
}

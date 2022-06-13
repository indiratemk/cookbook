package com.sparkle.cookbook.addrecipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sparkle.cookbook.R
import com.sparkle.cookbook.ui.theme.DefaultButton
import com.sparkle.cookbook.ui.theme.MultiLineInput
import com.sparkle.cookbook.ui.theme.SingleLineInput
import com.sparkle.cookbook.ui.theme.Subtitle
import com.sparkle.cookbook.ui.theme.SubtitleThin
import com.sparkle.cookbook.ui.theme.Title
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddRecipeScreen(
    goBack: () -> Unit
) {
    Scaffold {
        val bringIntoViewRequester = remember { BringIntoViewRequester() }
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            content = {
                TextButton(onClick = goBack) {
                    SubtitleThin(
                        text = "Cancel",
                        color = MaterialTheme.colors.secondary,
                        startIndent = 8.dp
                    )
                }
                Title(
                    text = LocalContext.current.getString(R.string.add_recipe_title),
                    indents = 16.dp
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .bringIntoViewRequester(bringIntoViewRequester)
                ) {
                    RecipeTitleField(bringIntoViewRequester, coroutineScope)
                    RecipeDescriptionField(bringIntoViewRequester, coroutineScope)
                    DefaultButton(
                        title = LocalContext.current.getString(R.string.add_recipe_create_button),
                        startSpace = 16.dp,
                        endSpace = 16.dp,
                        topSpace = 8.dp,
                        bottomSpace = 16.dp
                    ) {}
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeTitleField(
    bringIntoViewRequester: BringIntoViewRequester,
    coroutineScope: CoroutineScope
) {
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                bottom = 16.dp,
                end = 16.dp
            )
            .onFocusEvent { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeDescriptionField(
    bringIntoViewRequester: BringIntoViewRequester,
    coroutineScope: CoroutineScope
) {
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
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(
                start = 16.dp,
                bottom = 16.dp,
                end = 16.dp
            )
            .onFocusEvent { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
    )
}

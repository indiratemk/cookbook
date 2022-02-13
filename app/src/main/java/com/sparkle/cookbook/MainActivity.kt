package com.sparkle.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.coroutineScope
import com.sparkle.cookbook.ui.theme.CookBookTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: RecipeViewModel by lazy {
        RecipeViewModelFactory((application as CookBookApplication).database.recipeDao())
            .create(RecipeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.coroutineScope.launch {
            viewModel.getRecipes().collect() { recipes ->
                println(recipes)
            }
        }

        setContent {
            CookBookTheme {
                LazyColumn(content = {
                    item { Title(getString(R.string.recipes_list_title)) }
                    item { Recipes(viewModel = viewModel) }
                })
            }
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = Typography().h4
    )
}

@Composable
fun Recipes(viewModel: RecipeViewModel) {
    val recipes = viewModel.getRecipes().collectAsState(initial = emptyList()).value

    LazyRow(
        content = {
            item { Spacer(Modifier.size(8.dp)) }
            for (recipe in recipes) {
                item {
                    Column(
                        modifier = Modifier.width(120.dp)
                    ) {
                        val image = loadImage(url = recipe.imageUrl)
                        image.value?.let { bitmap ->
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .heightIn(min = 120.dp, max = 120.dp)
                                    .widthIn(min = 120.dp, max = 120.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(text = recipe.title)
                        Text(text = recipe.description)
                    }

                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    )
}

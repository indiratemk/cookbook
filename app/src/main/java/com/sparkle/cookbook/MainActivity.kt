package com.sparkle.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
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
                    item { Title(title = getString(R.string.recipes_list_title)) }
                    item { Subtitle(subtitle = getString(R.string.recipes_popular_title)) }
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
        style = Typography().h4,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun Subtitle(subtitle: String) {
    Text(
        text = subtitle,
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
fun Recipes(viewModel: RecipeViewModel) {
    val recipes = viewModel.getRecipes().collectAsState(initial = emptyList()).value

    LazyRow(
        content = {
            item { Spacer(Modifier.size(16.dp)) }
            for (recipe in recipes) {
                item {
                    Column(
                        modifier = Modifier.width(120.dp)
                    ) {
                        val image = loadImage(
                            url = recipe.imageUrl,
                            defaultImage = R.drawable.empty_plate
                        )
                        image.value?.let { bitmap ->
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                elevation = 4.dp
                            ) {
                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .heightIn(min = 120.dp, max = 120.dp)
                                        .widthIn(min = 120.dp, max = 120.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Text(
                            text = recipe.title,
                            style = Typography().body2,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    )
}

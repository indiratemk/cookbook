package com.sparkle.cookbook.recipes

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sparkle.cookbook.CookBookApplication
import com.sparkle.cookbook.R
import com.sparkle.cookbook.recipes.data.Recipe
import com.sparkle.cookbook.recipes.tea.RecipesFeature
import com.sparkle.cookbook.recipes.tea.provideFeature
import com.sparkle.cookbook.teacore.Application
import com.sparkle.cookbook.ui.theme.CookBookTheme
import com.sparkle.cookbook.util.loadImage

class MainActivity : ComponentActivity() {

    private val feature by lazy {
        provideFeature((application as CookBookApplication).database.recipeDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                feature.Application { state, _ -> RecipesScreen(state = state) }
            }
        }
    }
}

@Composable
fun RecipesScreen(state: RecipesFeature.State) {
    LazyColumn(content = {
        item { Title(title = LocalContext.current.getString(R.string.recipes_list_title)) }
        item { Subtitle(subtitle = LocalContext.current.getString(R.string.recipes_popular_title)) }
        item { RecipeItems(recipes = state.recipes) }
    })
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
fun RecipeItems(recipes: List<Recipe>) {
    LazyRow(
        content = {
            item { Spacer(Modifier.size(16.dp)) }
            for (recipe in recipes) {
                item { RecipeItem(recipe = recipe) }
            }
        }
    )
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Column(
        modifier = Modifier.width(120.dp)
    ) {
        RecipeImage(imageUrl = recipe.imageUrl)
        RecipeTitle(title = recipe.title)
    }
    Spacer(Modifier.size(16.dp))
}

@Composable
fun RecipeTitle(title: String) {
    Text(
        text = title,
        style = Typography().body2,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun RecipeImage(imageUrl: String) {
    val image = loadImage(
        url = imageUrl,
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
}

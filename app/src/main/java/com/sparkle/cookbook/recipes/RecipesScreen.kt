package com.sparkle.cookbook.recipes

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
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sparkle.cookbook.ActionClearableReference
import com.sparkle.cookbook.R
import com.sparkle.cookbook.recipes.data.Recipe
import com.sparkle.cookbook.recipes.tea.IRecipesFeatureProvider
import com.sparkle.cookbook.recipes.tea.RecipesFeature
import com.sparkle.cookbook.teacore.Feature
import com.sparkle.cookbook.teacore.asComposeState
import com.sparkle.cookbook.ui.theme.Body
import com.sparkle.cookbook.ui.theme.DefaultButton
import com.sparkle.cookbook.ui.theme.Subtitle
import com.sparkle.cookbook.ui.theme.Title
import com.sparkle.cookbook.util.loadImage

@Composable
fun RecipesScreen(
    openAddRecipe: () -> Unit
) {
    val featureProvider: ActionClearableReference<IRecipesFeatureProvider> =
        IRecipesFeatureProvider.ref

    val feature: Feature<RecipesFeature.Msg, RecipesFeature.State, RecipesFeature.Eff> =
        featureProvider.get().feature

    val state by feature.asComposeState()

    LazyColumn(content = {
        item {
            Title(
                text = LocalContext.current.getString(R.string.recipes_list_title),
                indents = 16.dp
            )
        }
        item {
            Subtitle(
                text = LocalContext.current.getString(R.string.recipes_popular_title),
                startIndent = 16.dp,
                topIndent = 8.dp,
                endIndent = 16.dp,
                bottomIndent = 16.dp,
            )
        }
        item { RecipeItems(recipes = state.recipes) }
        item { DefaultButton(
            title = "Add Recipe",
            onClick = openAddRecipe,
        ) }
    })
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
        Body(text = recipe.title)
    }
    Spacer(Modifier.size(16.dp))
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

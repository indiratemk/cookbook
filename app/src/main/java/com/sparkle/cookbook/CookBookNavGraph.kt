package com.sparkle.cookbook

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sparkle.cookbook.addrecipe.AddRecipeScreen
import com.sparkle.cookbook.recipes.RecipesScreen

@Composable
fun CookBookNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.RECIPES) {
        composable(Routes.RECIPES) {
            RecipesScreen(openAddRecipe = { navController.navigate(Routes.ADD_RECIPE) })
        }
        composable(Routes.ADD_RECIPE) {
            AddRecipeScreen(goBack = { navController.navigateUp() })
        }
    }
}

object Routes {
    const val ADD_RECIPE = "add_recipe"
    const val RECIPES = "recipes"
}

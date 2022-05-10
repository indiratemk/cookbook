package com.sparkle.cookbook

import com.sparkle.cookbook.recipes.domain.RecipeDao
import com.sparkle.cookbook.recipes.tea.IRecipesFeatureProvider
import com.sparkle.cookbook.recipes.tea.RecipesFeatureProvider

class FeatureProvidersHolder(
    recipeDao: RecipeDao
) {
    private val recipesFeatureRef = ActionClearableReference<IRecipesFeatureProvider> {
        RecipesFeatureProvider(recipeDao)
    }

    fun initAllFeatureProviders() {
        IRecipesFeatureProvider.ref = recipesFeatureRef
    }
}

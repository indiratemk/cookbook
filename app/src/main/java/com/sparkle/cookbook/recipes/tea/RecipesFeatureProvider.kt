package com.sparkle.cookbook.recipes.tea

import com.sparkle.cookbook.recipes.domain.RecipeDao
import com.sparkle.cookbook.teacore.Feature
import com.sparkle.cookbook.teacore.SyncFeature
import com.sparkle.cookbook.teacore.wrapWithEffectHandler

fun provideFeature(recipeDao: RecipeDao):
    Feature<RecipesFeature.Msg, RecipesFeature.State, RecipesFeature.Eff> {
    return SyncFeature(
        initialState = RecipesFeature.initialState(),
        reducer = RecipesFeature::reducer
    ).wrapWithEffectHandler(
        effectHandler = RecipesEffectHandler(recipeDao),
        initialEffects = RecipesFeature.initialEffects()
    )
}

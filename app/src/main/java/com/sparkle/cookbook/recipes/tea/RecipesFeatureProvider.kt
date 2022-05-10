package com.sparkle.cookbook.recipes.tea

import com.sparkle.cookbook.recipes.domain.RecipeDao
import com.sparkle.cookbook.teacore.Feature
import com.sparkle.cookbook.teacore.SyncFeature
import com.sparkle.cookbook.teacore.wrapWithEffectHandler

class RecipesFeatureProvider(
    recipeDao: RecipeDao
) : IRecipesFeatureProvider {

    override val feature: Feature<RecipesFeature.Msg, RecipesFeature.State, RecipesFeature.Eff> =
        SyncFeature(
            initialState = RecipesFeature.initialState(),
            reducer = RecipesFeature::reducer
        ).wrapWithEffectHandler(
            effectHandler = RecipesEffectHandler(recipeDao),
            initialEffects = RecipesFeature.initialEffects()
        )
}

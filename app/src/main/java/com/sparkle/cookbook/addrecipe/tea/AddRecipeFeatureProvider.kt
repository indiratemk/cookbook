package com.sparkle.cookbook.addrecipe.tea

import com.sparkle.cookbook.recipes.domain.RecipeDao
import com.sparkle.cookbook.teacore.Feature
import com.sparkle.cookbook.teacore.SyncFeature
import com.sparkle.cookbook.teacore.wrapWithEffectHandler

class AddRecipeFeatureProvider(
    recipeDao: RecipeDao
) : IAddRecipeFeatureProvider {
    override val feature: Feature<AddRecipeFeature.Msg, AddRecipeFeature.State, AddRecipeFeature.Eff> =
        SyncFeature(
            initialState = AddRecipeFeature.initialState(),
            reducer = AddRecipeFeature::reducer
        ).wrapWithEffectHandler(
            effectHandler = AddRecipeEffectHandler(recipeDao)
        )
}

package com.sparkle.cookbook.recipes.tea

import com.sparkle.cookbook.ActionClearableReference
import com.sparkle.cookbook.teacore.Feature

interface IRecipesFeatureProvider {
    val feature: Feature<RecipesFeature.Msg, RecipesFeature.State, RecipesFeature.Eff>
    companion object {
        lateinit var ref: ActionClearableReference<IRecipesFeatureProvider>
    }
}

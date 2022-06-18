package com.sparkle.cookbook.addrecipe.tea

import com.sparkle.cookbook.ActionClearableReference
import com.sparkle.cookbook.teacore.Feature

interface IAddRecipeFeatureProvider {
    val feature: Feature<AddRecipeFeature.Msg, AddRecipeFeature.State, AddRecipeFeature.Eff>
    companion object {
        lateinit var ref: ActionClearableReference<IAddRecipeFeatureProvider>
    }
}

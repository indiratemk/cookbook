package com.sparkle.cookbook.recipes.tea

import com.sparkle.cookbook.recipes.domain.RecipeDao
import com.sparkle.cookbook.teacore.EffectHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesEffectHandler(
    private val recipeDao: RecipeDao
) : EffectHandler<RecipesFeature.Eff, RecipesFeature.Msg> {
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private var listener: ((RecipesFeature.Msg) -> Unit)? = null

    override fun setListener(listener: (RecipesFeature.Msg) -> Unit) {
        this.listener = { msg -> listener(msg) }
    }

    override fun handleEffect(eff: RecipesFeature.Eff) {
        when (eff) {
            is RecipesFeature.Eff.LoadRecipes -> scope.launch {
                recipeDao.getRecipes().collect { recipes ->
                    val msg = RecipesFeature.Msg.OnRecipesLoaded(recipes = recipes)
                    listener?.invoke(msg)
                }
            }
        }
    }

    override fun cancel() {
        scope.cancel()
    }
}

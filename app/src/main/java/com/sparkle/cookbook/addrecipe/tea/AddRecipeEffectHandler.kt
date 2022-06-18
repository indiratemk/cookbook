package com.sparkle.cookbook.addrecipe.tea

import com.sparkle.cookbook.recipes.data.Recipe
import com.sparkle.cookbook.recipes.domain.RecipeDao
import com.sparkle.cookbook.teacore.EffectHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*

class AddRecipeEffectHandler(
    private val recipeDao: RecipeDao
) : EffectHandler<AddRecipeFeature.Eff, AddRecipeFeature.Msg> {
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private var listener: ((AddRecipeFeature.Msg) -> Unit)? = null

    override fun setListener(listener: (AddRecipeFeature.Msg) -> Unit) {
        this.listener = { msg -> listener(msg) }
    }

    override fun handleEffect(eff: AddRecipeFeature.Eff) {
        when (eff) {
            is AddRecipeFeature.Eff.SaveRecipe -> scope.launch {
                recipeDao.addRecipe(
                    Recipe(
                        id = UUID.randomUUID().toString(),
                        title = eff.title,
                        description = eff.description,
                        imageUrl = "https://image.sciencenorway.no/1438480.jpg?imageId=1438480&panow=0&panoh=0&panox=0&panoy=0&heightw=0&heighth=0&heightx=0&heighty=0&width=1200&height=900"
                    )
                )
            }
        }
    }

    override fun cancel() {
        scope.cancel()
    }
}

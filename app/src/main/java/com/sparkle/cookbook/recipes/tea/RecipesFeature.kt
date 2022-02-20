package com.sparkle.cookbook.recipes.tea

import com.sparkle.cookbook.recipes.data.Recipe

typealias ReducerResult = Pair<RecipesFeature.State, Set<RecipesFeature.Eff>>

object RecipesFeature {
    fun initialState(): State = State(recipes = emptyList())
    fun initialEffects(): Set<Eff> = setOf(Eff.LoadRecipes)

    data class State(
        val recipes: List<Recipe>
    )

    sealed class Msg {
        data class OnRecipesLoaded(val recipes: List<Recipe>) : Msg()
    }

    sealed class Eff {
        object LoadRecipes : Eff()
    }

    fun reducer(msg: Msg, state: State): ReducerResult = when (msg) {
        is Msg.OnRecipesLoaded -> state.copy(recipes = msg.recipes) to emptySet()
    }
}

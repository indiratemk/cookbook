package com.sparkle.cookbook.addrecipe.tea

typealias ReducerResult = Pair<AddRecipeFeature.State, Set<AddRecipeFeature.Eff>>

object AddRecipeFeature {
    fun initialState(): State = State(title = null, description = null, isLoading = false)
    fun initialEffects(): Set<Eff> = emptySet()

    data class State(
        val title: String?,
        val description: String?,
        val isLoading: Boolean,
        val isRecipeSaved: Boolean = false
    )

    sealed class Msg {
        object OnSaveRecipeClicked : Msg()
        data class OnTitleChanged(val title: String) : Msg()
        data class OnDescriptionChanged(val description: String) : Msg()
        object OnRecipeSaved : Msg()
    }

    sealed class Eff {
        data class SaveRecipe(
            val title: String,
            val description: String
        ) : Eff()
    }

    fun reducer(msg: Msg, state: State): ReducerResult = when (msg) {
        is Msg.OnTitleChanged -> state.copy(title = msg.title) to emptySet()
        is Msg.OnDescriptionChanged -> state.copy(description = msg.description) to emptySet()
        Msg.OnSaveRecipeClicked -> state.copy(isLoading = true).handleSavingRecipe()
        Msg.OnRecipeSaved -> state.copy(isRecipeSaved = true) to emptySet()
    }

    private fun State.handleSavingRecipe(): ReducerResult =
        if (title.isNullOrBlank() || description.isNullOrBlank()) {
            this to emptySet()
        } else {
            this to setOf(Eff.SaveRecipe(title, description))
        }
}

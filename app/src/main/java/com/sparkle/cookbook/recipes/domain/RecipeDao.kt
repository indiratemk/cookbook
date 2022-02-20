package com.sparkle.cookbook.recipes.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sparkle.cookbook.recipes.data.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert
    suspend fun addRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getRecipes(): Flow<List<Recipe>>
}

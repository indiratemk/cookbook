package com.sparkle.cookbook.recipes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String
)

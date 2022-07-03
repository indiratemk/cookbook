package com.sparkle.cookbook.recipes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sparkle.cookbook.recipes.domain.DateConverter
import java.util.Date

@Entity
@TypeConverters(DateConverter::class)
data class Recipe(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val creationDate: Date
)

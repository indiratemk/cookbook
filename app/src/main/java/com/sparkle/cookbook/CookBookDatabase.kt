package com.sparkle.cookbook

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1)
abstract class CookBookDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: CookBookDatabase? = null

        fun getDatabase(context: Context): CookBookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CookBookDatabase::class.java,
                    "cook_book_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}

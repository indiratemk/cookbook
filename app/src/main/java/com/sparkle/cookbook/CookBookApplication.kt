package com.sparkle.cookbook

import android.app.Application

class CookBookApplication : Application() {
    private val database: CookBookDatabase by lazy { CookBookDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        val dao = database.recipeDao()
        FeatureProvidersHolder(dao).initAllFeatureProviders()
    }
}

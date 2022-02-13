package com.sparkle.cookbook

import android.app.Application

class CookBookApplication : Application() {
    val database: CookBookDatabase by lazy { CookBookDatabase.getDatabase(this) }
}

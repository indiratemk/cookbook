package com.sparkle.cookbook.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sparkle.cookbook.CookBookNavGraph
import com.sparkle.cookbook.ui.theme.CookBookTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                CookBookNavGraph()
            }
        }
    }
}

package com.example.androidstudyapp.ui

import android.app.Application
import com.example.androidstudyapp.RecipeModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipesApplication : Application() {

    private lateinit var recipeModule: RecipeModule

    override fun onCreate() {
        super.onCreate()

        recipeModule = RecipeModule()
    }
}

package com.example.androidstudyapp.ui

import android.app.Application
import com.example.androidstudyapp.RecipeModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipesApplication : Application() {

    companion object {
        private var _instance: Application? = null
        val instance get() = requireNotNull(_instance)
    }

    lateinit var recipeModule: RecipeModule

    override fun onCreate() {
        super.onCreate()
        _instance = this

        recipeModule = RecipeModule()
    }
}

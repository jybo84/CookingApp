package com.example.androidstudyapp.ui.recipe.recipe

import android.app.Application

class RecipesApplication {

    companion object {
        private var _instance: Application? = null
        val instance get() = requireNotNull(_instance)
    }



    }
//    override fun onCreate() {
//        super.onCreate()
//        _instance = this
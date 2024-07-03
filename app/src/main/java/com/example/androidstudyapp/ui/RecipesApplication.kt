package com.example.androidstudyapp.ui

import android.app.Application
import com.example.androidstudyapp.AppContainer

class RecipesApplication : Application() {

    companion object {
        private var _instance: Application? = null
        val instance get() = requireNotNull(_instance)
    }

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        _instance = this

        appContainer = AppContainer()


    }
}

package com.example.androidstudyapp.ui

import android.app.Application

class RecipesApplication: Application() {

    companion object {
        private var _instance: Application? = null
        val instance get() = requireNotNull(_instance)
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }
}


//package com.example.androidstudyapp.ui
//
//import android.app.Application
//
//class RecipesApplication: Application() {
//
//    companion object {
//        private var _instance: Application? = null
//        val instance get() = requireNotNull(_instance)
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        _instance = this
//    }
//}

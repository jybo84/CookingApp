package com.example.androidstudyapp

import com.example.androidstudyapp.data.API_BASE_URL
import com.example.androidstudyapp.data.RecipeApiService
import com.example.androidstudyapp.data.RecipesRepository
import com.example.androidstudyapp.data.db.DataBase
import com.example.androidstudyapp.di.CategoriesListViewModelFactory
import com.example.androidstudyapp.di.FavouritesViewModelFactory
import com.example.androidstudyapp.ui.RecipesApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("$API_BASE_URL/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val dataBase = DataBase.getDataBase(RecipesApplication.instance)

    private val recipeApiService: RecipeApiService = retrofit.create(RecipeApiService::class.java)

    private val categoriesDao = dataBase.getCategoryDao()

    private val recipesDao = dataBase.getRecipesDao()

     val repository = RecipesRepository(
        recipesDao = recipesDao,
        categoriesDao = categoriesDao,
        recipeApiService = recipeApiService
    )

    val categoriesListViewModelFactory = CategoriesListViewModelFactory(repository)
    val favouriteModelFactory = FavouritesViewModelFactory(repository)
}
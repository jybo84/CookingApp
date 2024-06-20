package com.example.androidstudyapp.model

import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {
    @GET("recipe/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Recipe

    @GET("recipes")                            //TODO здесь как-то по другому
    suspend fun getListRecipesById(@Path("id") id: Int): List<Recipe>

    @GET("category/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): Category

    @GET("category/{id}/recipes")
    suspend fun getListRecipesByIdCategory(@Path("id") id: Int): Category

    @GET("category/")
    suspend fun getListCategory(): Category
}
package com.example.androidstudyapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApiService {
    @GET("recipe/{id}")
    fun getRecipeById(@Path("id") id: Int): Call<Recipe>

    @GET("category/{id}/recipes")
    fun getListRecipesByIdCategory(@Path("id") id: Int): Call<List<Recipe>>

    @GET("category")
    fun getListCategory(): Call<List<Category>>

    @GET("recipes")
    fun getListRecipesById(@Query("ids") ids: List<Int>): Call<List<Recipe>>

    @GET("category/{id}")
    fun getCategoryById(@Path("id") id: Int): Call<Category>
}


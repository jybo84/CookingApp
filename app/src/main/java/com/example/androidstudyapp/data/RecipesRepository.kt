package com.example.androidstudyapp.data

import com.example.androidstudyapp.model.RecipeApiService
import retrofit2.Call

class RecipesRepository: RecipeApiService {

    override suspend fun getRecipeById(id: Int): Call<Recipe> {
        return burgerRecipes.find { it.id == id }

    }

    override suspend fun getListRecipesById(id: Int): Call<List<Recipe>> {
            return if (categoryId == 0)
        burgerRecipes
    else
        emptyList()
    }

    override suspend fun getCategoryById(id: Int): Call<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun getListRecipesByIdCategory(id: Int): Call<Category> {
            return if (categoryId == 0)
            burgerRecipes
            else
            emptyList()
    }

    override suspend fun getListCategory(): Call<List<Category>> {
        return categories
    }
}
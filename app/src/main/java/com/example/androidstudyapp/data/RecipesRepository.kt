package com.example.androidstudyapp.data

import com.example.androidstudyapp.model.RecipeApiService

class RecipesRepository: RecipeApiService {
    override suspend fun getRecipeById(id: Int): Recipe {
        TODO("Not yet implemented")
    }

    override suspend fun getListRecipesById(id: Int): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryById(id: Int): Category {
        TODO("Not yet implemented")
    }

    override suspend fun getListRecipesByIdCategory(id: Int): Category {
        TODO("Not yet implemented")
    }

    override suspend fun getListCategory(): Category {
        TODO("Not yet implemented")
    }
}
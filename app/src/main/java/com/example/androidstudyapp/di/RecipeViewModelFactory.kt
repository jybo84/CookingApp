package com.example.androidstudyapp.di

import com.example.androidstudyapp.data.RecipesRepository
import com.example.androidstudyapp.ui.recipe.recipe.RecipeViewModel

class RecipeViewModelFactory(
    private val recipesRepository: RecipesRepository
) : Factory<RecipeViewModel> {

    override fun create(): RecipeViewModel {
        return RecipeViewModel(recipesRepository)
    }
}
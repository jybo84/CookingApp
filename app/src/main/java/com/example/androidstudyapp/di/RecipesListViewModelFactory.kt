package com.example.androidstudyapp.di

import com.example.androidstudyapp.data.RecipesRepository
import com.example.androidstudyapp.ui.recipe.listRecipe.RecipesListViewModel

class RecipesListViewModelFactory(
    private val recipesRepository: RecipesRepository
): Factory<RecipesListViewModel> {
    override fun create(): RecipesListViewModel {
        return RecipesListViewModel(recipesRepository)
    }
}
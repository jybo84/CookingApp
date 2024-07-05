package com.example.androidstudyapp.di

import com.example.androidstudyapp.data.RecipesRepository
import com.example.androidstudyapp.ui.recipe.favourites.FavouritesViewModel

class FavouritesViewModelFactory(
    private val recipesRepository: RecipesRepository
) : Factory<FavouritesViewModel> {

    override fun create(): FavouritesViewModel {
        return FavouritesViewModel(recipesRepository)
    }
}
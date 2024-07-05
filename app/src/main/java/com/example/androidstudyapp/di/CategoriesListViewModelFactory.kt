package com.example.androidstudyapp.di

import com.example.androidstudyapp.data.RecipesRepository
import com.example.androidstudyapp.ui.category.CategoriesListViewModel

class CategoriesListViewModelFactory(
    private val recipesRepository: RecipesRepository
) : Factory<CategoriesListViewModel> {

    override fun create(): CategoriesListViewModel {
        return CategoriesListViewModel(recipesRepository)
    }
}
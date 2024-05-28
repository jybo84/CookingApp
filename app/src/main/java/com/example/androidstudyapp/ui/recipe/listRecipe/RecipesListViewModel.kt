package com.example.androidstudyapp.ui.recipe.listRecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipesListViewModel : ViewModel() {
    data class RecipeListState(
        var categoryId: Int? = null,
        var categoryName: String? = null,
        var categoryImageUrl: String? = null,
    )

     private val _state = MutableLiveData(RecipeListState())
    val state: LiveData<RecipeListState> = _state
}
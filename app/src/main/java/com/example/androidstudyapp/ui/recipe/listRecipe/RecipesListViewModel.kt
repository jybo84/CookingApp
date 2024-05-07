package com.example.androidstudyapp.ui.recipe.listRecipe

import androidx.lifecycle.ViewModel

class RecipesListViewModel : ViewModel(){
    data class RecipeState(
        var categoryId: Int? = null,
        var categoryName: String? = null,
        var categoryImageUrl: String? = null,
    )
}
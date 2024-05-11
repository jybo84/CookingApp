package com.example.androidstudyapp.ui.recipe.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidstudyapp.data.Recipe

class RecipeViewModel : ViewModel() {

    private val _state = MutableLiveData(RecipeState())
    val state: LiveData<RecipeState> = _state

    data class RecipeState(
        val recipe: Recipe? = null,
        val recipeImageUrl: String? = null,
        val isFavourite: Boolean = false,
        val quantityPortions: Int = 1,
    )

    init {
        _state.value = RecipeState(isFavourite = true)
        Log.i("!!!", "massage")
    }
}
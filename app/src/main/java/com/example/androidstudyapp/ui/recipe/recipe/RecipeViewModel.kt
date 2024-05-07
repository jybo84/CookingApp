package com.example.androidstudyapp.ui.recipe.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidstudyapp.data.Recipe

class RecipeViewModel : ViewModel() {

    data class RecipeState(
        var recipe: Recipe? = null,
        var recipeImageUrl: String? = null,
        var isFavourite: Boolean = false,
    )

    private val _state = MutableLiveData(RecipeState())
    val state: LiveData<RecipeState> = _state

    init {
        _state.value = RecipeState(isFavourite = true)
        Log.i("!!!", "massage")
    }
}
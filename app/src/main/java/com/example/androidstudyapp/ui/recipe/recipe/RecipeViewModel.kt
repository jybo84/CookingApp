package com.example.androidstudyapp.ui.recipe.recipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidstudyapp.data.ImageUtils
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val _state = MutableLiveData(RecipeState())
    val state: LiveData<RecipeState> = _state

    private val recipeRepository = RecipesRepository()

    data class RecipeState(
        val recipe: Recipe? = null,
        val isFavourite: Boolean = false,
        var portionsCount: Int = 1,
        val recipeImageUrl: String? = null,
    )

    fun loadRecipe(recipeId: Int) {
        viewModelScope.launch {
            val recipe = recipeRepository.getRecipeById(recipeId)
            _state.postValue(
                RecipeState(
                    recipe = recipe,
                    isFavourite = getFavorites().contains(recipeId),
                    portionsCount = state.value?.portionsCount ?: 1,
                    recipeImageUrl = recipe?.imageUrl?.let { ImageUtils.getImageFullUrl(it) }
                )
            )
        }
    }

    private suspend fun getFavorites(): List<Int> {
        return recipeRepository.getListFavouriteRecipes().map { it.id }
    }

    fun onFavoritesClicked() {
        viewModelScope.launch {
            val favourite = _state.value?.isFavourite ?: false
            _state.value?.recipe?.let { recipeRepository.updateRecipe(it.copy(isFavorite = !favourite)) }
            _state.value = _state.value?.copy(isFavourite = !favourite)
        }
    }


    fun setCountPortions(count: Int): Int? {
        state.value?.portionsCount = count
        return state.value?.portionsCount
    }
}

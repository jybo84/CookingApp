package com.example.androidstudyapp.ui.recipe.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val recipesRepository:  RecipesRepository
) : ViewModel() {

    data class FavouritesState(
        val dataSet: List<Recipe>? = emptyList()
    )

    private val _state = MutableLiveData(FavouritesState())
    val state: LiveData<FavouritesState> = _state

    fun loadFavourites() {
        viewModelScope.launch {
            _state.postValue(
                FavouritesState(
                    dataSet = recipesRepository.getListFavouriteRecipes()

                )
            )
        }
    }
}

package com.example.androidstudyapp.ui.recipe.favourites

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidstudyapp.data.FAVORITE_PREFS_KEY
import com.example.androidstudyapp.data.FILE_COLLECTION_MY_ID
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository
import kotlinx.coroutines.launch

class FavouritesViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPrefs by lazy {
        application.getSharedPreferences(FILE_COLLECTION_MY_ID, Context.MODE_PRIVATE)
    }

    private val recipeRepository = RecipesRepository()

    data class FavouritesState(
        val dataSet: List<Recipe>? = emptyList()
    )

    private val _state = MutableLiveData(FavouritesState())
    val state: LiveData<FavouritesState> = _state

    private fun getFavorites(): Set<String> {
        val savedList: Set<String> =
            sharedPrefs.getStringSet(FAVORITE_PREFS_KEY, emptySet()) ?: emptySet()
        return HashSet(savedList)
    }

    fun loadFavourites() {
        viewModelScope.launch {
            _state.postValue(
                FavouritesState(
                    dataSet = recipeRepository.getRecipesByIds(getFavorites().map { it.toInt() })
                )
            )
        }
    }
}

package com.example.androidstudyapp.ui.recipe.recipe

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidstudyapp.data.FAVORITE_PREFS_KEY
import com.example.androidstudyapp.data.FILE_COLLECTION_MY_ID
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.model.STUB

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val _state = MutableLiveData(RecipeState())
    val state: LiveData<RecipeState> = _state

    private val sharedPrefs by lazy {
        application.getSharedPreferences(FILE_COLLECTION_MY_ID, Context.MODE_PRIVATE)
    }

    data class RecipeState(
        val recipe: Recipe? = null,
        val isFavourite: Boolean = false,
        val portionsCount: Int = 1,
    )

    init {
        _state.value = RecipeState(isFavourite = true)
        Log.i("!!!", "massage")
    }

    fun loadRecipe(recipeId: Int) {
        //TODO 'load from network'
        RecipeState(
            recipe = STUB.getRecipeById(recipeId),
            isFavourite = getFavorites().contains(recipeId.toString()),
            portionsCount = state.value?.portionsCount ?: 1
        )
    }
    fun getFavorites(): MutableSet<String> {
        val savedList: Set<String> =
            sharedPrefs.getStringSet(FAVORITE_PREFS_KEY, emptySet()) ?: emptySet()
        return HashSet(savedList)
    }

    fun onFavoritesClicked(){
        val myListRecipes = getFavorites()
        if (myListRecipes.contains(state.value?.recipe?.id.toString()))
            myListRecipes.remove(state.value?.recipe?.toString())
        else state.value?.recipe?.toString()?.let { myListRecipes.add(it) }

        _state.value = state.value?.copy(isFavourite = getFavorites().contains(state.value.toString()))
    }
    private fun saveFavorites(listIdFavouritesRecipes: Set<String>) {
        with(sharedPrefs.edit()) {
            putStringSet(FAVORITE_PREFS_KEY, listIdFavouritesRecipes)
        }.apply()
    }
}
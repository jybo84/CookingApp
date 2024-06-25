package com.example.androidstudyapp.ui.recipe.recipe

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidstudyapp.data.FAVORITE_PREFS_KEY
import com.example.androidstudyapp.data.FILE_COLLECTION_MY_ID
import com.example.androidstudyapp.data.ImageUtils
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository
import java.util.concurrent.Executors

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val _state = MutableLiveData(RecipeState())
    val state: LiveData<RecipeState> = _state

    private val sharedPrefs by lazy {
        application.getSharedPreferences(FILE_COLLECTION_MY_ID, Context.MODE_PRIVATE)
    }

    private val recipeRepository = RecipesRepository()

    private val threadPool = Executors.newFixedThreadPool(10)

    data class RecipeState(
        val recipe: Recipe? = null,
        val isFavourite: Boolean = false,
        var portionsCount: Int = 1,
        val recipeImageUrl: String? = null,
    )

    init {
        _state.value = RecipeState(isFavourite = true)
        Log.i("!!!", "massage")
    }

    fun loadRecipe(recipeId: Int) {
        threadPool.execute {

            //TODO 'load from network'
            val recipe = recipeRepository.getRecipeById(recipeId)
            _state.postValue(
                RecipeState(
                    recipe = recipe,
                    isFavourite = getFavorites().contains(recipeId.toString()),
                    portionsCount = state.value?.portionsCount ?: 1,
                    recipeImageUrl = ImageUtils.getImageFullUrl(recipe?.imageUrl)
                )
            )
        }
    }

    private fun getFavorites(): MutableSet<String> {
        val savedList: Set<String> =
            sharedPrefs.getStringSet(FAVORITE_PREFS_KEY, emptySet()) ?: emptySet()
        return HashSet(savedList)
    }

    fun onFavoritesClicked() {
        val myListRecipes = getFavorites()
        if (myListRecipes.contains(state.value?.recipe?.id.toString()))
            myListRecipes.remove(state.value?.recipe?.id.toString())
        else state.value?.recipe?.id?.toString()?.let { myListRecipes.add(it) }

        saveFavorites(myListRecipes)

        _state.value =
            state.value?.copy(isFavourite = getFavorites().contains(state.value?.recipe?.id.toString()))
    }

    private fun saveFavorites(listIdFavouritesRecipes: Set<String>) {
        with(sharedPrefs.edit()) {
            putStringSet(FAVORITE_PREFS_KEY, listIdFavouritesRecipes)
        }.apply()
    }

     fun setCountPortions(count: Int): Int? {
        state.value?.portionsCount = count
        return state.value?.portionsCount
    }
}

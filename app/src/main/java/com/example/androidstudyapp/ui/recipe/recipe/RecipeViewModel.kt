package com.example.androidstudyapp.ui.recipe.recipe

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
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
        val recipeImage: Drawable? = null,
    )

    init {
        _state.value = RecipeState(isFavourite = true)
        Log.i("!!!", "massage")
    }

    fun loadRecipe(recipeId: Int) {
        //TODO 'load from network'
        val recipe = STUB.getRecipeById(recipeId)
        _state.value = RecipeState(
            recipe = recipe,
            isFavourite = getFavorites().contains(recipeId.toString()),
            portionsCount = state.value?.portionsCount ?: 1,
            recipeImage = getImageOfRecipe(recipe?.imageUrl)
        )
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

    private fun getImageOfRecipe(imageUrl: String?): Drawable? {

        try {
            val ims = imageUrl?.let { getApplication<Application>().assets.open(it) }
            val picture = Drawable.createFromStream(ims, null)
            return picture
        } catch (ex: Exception) {
            Log.e("mylog", "Error: $ex")
            return null
        }
    }
}
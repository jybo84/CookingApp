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

class RecipeViewModel(application: Application) :
    AndroidViewModel(application) { //TODO что такое Application

    private val _state = MutableLiveData(RecipeState())
    val state: LiveData<RecipeState> = _state

    private val sharedPrefs by lazy {
        getApplication<Application>().applicationContext.getSharedPreferences(  //TODO второй способ
            FILE_COLLECTION_MY_ID, Context.MODE_PRIVATE
        ) //TODO???, что это, как по другому
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
        //  TODO(load from network)
        _state.value = RecipeState(
            recipe = STUB.getRecipeById(recipeId),
            isFavourite = getFavorites().contains(recipeId.toString()),
            portionsCount = state.value?.portionsCount ?: 1
        )
        //Метод принимает id рецепта и присваивает полученный из хранилища рецепт соответствующему свойству стейта RecipeState.


    }

    private fun getFavorites(): MutableSet<String> {
        val savedList: Set<String> =
            sharedPrefs.getStringSet(FAVORITE_PREFS_KEY, emptySet()) ?: emptySet()

//        RecipeState(
//            isFavourite = getFavorites().isEmpty(),
//            portionsCount = 1 //TODO ????
//        )


        return HashSet(savedList)
//        Перенести метод getFavorites() во ViewModel для первичной инициализации поля стейта isFavorite актуальным значением.

    }

    fun onFavoritesClicked() {
        val myListRecipes = getFavorites()
        if (getFavorites().contains(state.value?.recipe?.id.toString()))
            myListRecipes.remove(state.value?.recipe?.id.toString())
        else myListRecipes.add(state.value?.recipe?.id.toString())

        saveFavorites(myListRecipes)

        _state.value =
            state.value?.copy(isFavourite = getFavorites().contains(state.value?.recipe?.id.toString()))

//         makeFavouriteHeard()
    }

    private fun saveFavorites(listIdFavouritesRecipes: Set<String>) {
        with(sharedPrefs.edit()) {
            putStringSet(FAVORITE_PREFS_KEY, listIdFavouritesRecipes)
        }.apply()
    }
}


//TODO что делать с методами откуда...



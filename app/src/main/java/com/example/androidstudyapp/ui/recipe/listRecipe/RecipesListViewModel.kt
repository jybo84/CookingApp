package com.example.androidstudyapp.ui.recipe.listRecipe

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.model.STUB

class RecipesListViewModel(application: Application) : AndroidViewModel(application) {
    data class RecipeListState(
        var categoryId: Recipe? = null,
        var categoryName: String? = null,
        var categoryImageUrl: Drawable? = null,
    )

     private val _state = MutableLiveData(RecipeListState())
    val state: LiveData<RecipeListState> = _state

    fun loadListRecipe(recipeId: Int){
        val recipe = STUB.getRecipeById(recipeId)
        _state.value = RecipeListState(
            categoryId = recipe,
            categoryName = recipe?.title,
            categoryImageUrl = getImageOfListRecipe(recipe?.imageUrl),
        )
    }
    private fun getImageOfListRecipe(imageUrl: String?): Drawable? {
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
package com.example.androidstudyapp.ui.recipe.listRecipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.ImageUtils
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository
import java.util.concurrent.Executors

class RecipesListViewModel(application: Application) : AndroidViewModel(application) {
    data class RecipeListState(
        var categoryName: String? = null,
        var categoryImageUrl: String? = null,
        val recipes: List<Recipe>? = emptyList(),
    )


    private val recipeRepository = RecipesRepository()

    private val _state = MutableLiveData(RecipeListState())
    val state: LiveData<RecipeListState> = _state

    private val threadPool = Executors.newFixedThreadPool(10)

    fun loadRecipes(categoryId: Int) {
        threadPool.execute() {
            val category = getCategoryById(categoryId)
            if (category != null) {
                _state.postValue(
                    RecipeListState(
                        categoryName = category.title,
                        categoryImageUrl = ImageUtils.getImageFullUrl(category.imageUrl),
                        recipes = recipeRepository.getRecipesByCategoryId(categoryId)
                    )
                )
            }
        }
    }

    private fun getCategoryById(id: Int): Category? {
        return recipeRepository.getCategories()?.find { it.id == id }
    }
}
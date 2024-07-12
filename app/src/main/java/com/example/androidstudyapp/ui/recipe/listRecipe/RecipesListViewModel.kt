package com.example.androidstudyapp.ui.recipe.listRecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.ImageUtils
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    data class RecipeListState(
        var categoryName: String? = null,
        var categoryImageUrl: String? = null,
        val recipes: List<Recipe>? = emptyList(),
    )

    private val _state = MutableLiveData(RecipeListState())
    val state: LiveData<RecipeListState> = _state

    fun loadRecipes(categoryId: Int) {

        viewModelScope.launch {

//            val categoryFromCache: Recipe? =
//                recipesRepository.getRecipesFromCache(categoryId).find { it.id == categoryId }
//
//            if (categoryFromCache != null) {
//                _state.postValue(
//                    RecipeListState(
//                        categoryName = categoryFromCache.title,
//                        categoryImageUrl = ImageUtils.getImageFullUrl(categoryFromCache.imageUrl),
//                        recipes = recipesRepository.getRecipesByCategoryId(categoryId)
//                    )
//                )
//            }


            val categoryFromCache: Category? =
                recipesRepository.getCategoriesFromCache().find { it.id == categoryId }

            if (categoryFromCache != null) {
                _state.postValue(
                    RecipeListState(
                        categoryName = categoryFromCache.title,
                        categoryImageUrl = ImageUtils.getImageFullUrl(categoryFromCache.imageUrl),
                        recipes = recipesRepository.getRecipesByCategoryId(categoryId)
                    )
                )
            }

            val categoryFromNetwork: Category? = getCategoryById(categoryId)
            if (categoryFromNetwork != null) {
                _state.postValue(
                    RecipeListState(
                        categoryName = categoryFromNetwork.title,
                        categoryImageUrl = ImageUtils.getImageFullUrl(categoryFromNetwork.imageUrl),
                        recipes = recipesRepository.getRecipesByCategoryId(categoryId)
                    )
                )
            }
        }
    }

    private suspend fun getCategoryById(id: Int): Category? {
        return recipesRepository.getCategories()?.find { it.id == id }
    }
}
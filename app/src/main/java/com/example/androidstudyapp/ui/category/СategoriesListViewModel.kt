package com.example.androidstudyapp.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.RecipesRepository
import kotlinx.coroutines.launch

class CategoriesListViewModel(application: Application) : AndroidViewModel(application) {

    data class StateCategoriesList(
        val categories: List<Category>? = emptyList(),
    )

    private val _state = MutableLiveData(StateCategoriesList())
    val state: LiveData<StateCategoriesList> = _state

    private val recipeRepository = RecipesRepository()

    fun loadCategoriesList() {
        viewModelScope.launch {

            _state.postValue(
                StateCategoriesList(
                    categories = recipeRepository.getCategoriesFromCache()
                )
            )

            _state.postValue(
                StateCategoriesList(
                    categories = recipeRepository.getCategories()
                )
            )
        }
    }
}
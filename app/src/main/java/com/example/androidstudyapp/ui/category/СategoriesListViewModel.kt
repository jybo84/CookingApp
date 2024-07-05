package com.example.androidstudyapp.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesListViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    data class StateCategoriesList(
        val categories: List<Category>? = emptyList(),
    )

    private val _state = MutableLiveData(StateCategoriesList())
    val state: LiveData<StateCategoriesList> = _state

    fun loadCategoriesList() {
        viewModelScope.launch {

            _state.postValue(
                StateCategoriesList(
                    categories = recipesRepository.getCategoriesFromCache()
                )
            )

            _state.postValue(
                StateCategoriesList(

                    categories = recipesRepository.getCategories()
                )
            )
        }
    }
}

package com.example.androidstudyapp.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.model.STUB

class CategoriesListViewModel(application: Application) : AndroidViewModel(application) {

    data class StateCategoriesList(
        val categories: List<Category> = emptyList(),
    )

    private val _state = MutableLiveData(StateCategoriesList())
    val state: LiveData<StateCategoriesList> = _state

    fun loadCategoriesList() {
        _state.value = StateCategoriesList(
            categories = STUB.getCategories()
        )
    }
}

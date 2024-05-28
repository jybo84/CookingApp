package com.example.androidstudyapp.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.model.STUB

class CategoriesListViewModel(application: Application) : AndroidViewModel(application) {

    data class StateCategoriesList(
        val id: Category? = null,
        val title: String? = null,
        val description: String? = null,
        val imageUrl: String? = null,
        )

    private val _state = MutableLiveData(StateCategoriesList())
    val state: LiveData<StateCategoriesList> = _state

    fun loadCategoriesList(categoryId: Int): Category? {
        val category: Category? = STUB.getCategories().find { it.id == categoryId }
        _state.value = StateCategoriesList(
            id = category,
            title = category?.title,
            description = category?.description,
            imageUrl = category?.imageUrl
        )
        return category
    }
}

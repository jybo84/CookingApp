package com.example.androidstudyapp.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CategoriesListViewModel(application: Application): AndroidViewModel(application) {

    data class StateCategoriesList(
        val id: Int? = null,
        val title: String? = null,
        val description: String? = null,
        val imageUrl: String? = null,
    )

    private val _state = MutableLiveData(StateCategoriesList())
     val state: LiveData<StateCategoriesList> = _state
}
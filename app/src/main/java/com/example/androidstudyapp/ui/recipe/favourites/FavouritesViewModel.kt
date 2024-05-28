package com.example.androidstudyapp.ui.recipe.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidstudyapp.data.Recipe

class FavouritesViewModel(application: Application) : AndroidViewModel(application) {

    data class FavouritesState(var dataSet: List<Recipe>? = null)

    private val _state = MutableLiveData(FavouritesState())
    val state: LiveData<FavouritesState> = _state
}


package com.example.androidstudyapp.ui.recipe.favourites

import androidx.lifecycle.ViewModel
import com.example.androidstudyapp.data.Recipe

class FavouritesViewModel : ViewModel() {

    data class FavouritesState(var dataSet: List<Recipe>)
}
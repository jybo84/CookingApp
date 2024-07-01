package com.example.androidstudyapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.androidstudyapp.data.Recipe

@Dao
interface RecipesDao {
    @Query("SELECT * FROM table_recipe")
    fun getListAllRecipes(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipeToList(category: List<Recipe>)

    @Query("SELECT * FROM table_recipe WHERE is_favorite = 1")
    fun getListFavouriteRecipes(): List<Recipe>

    @Upsert
    fun updateRecipe(recipe: Recipe)
}



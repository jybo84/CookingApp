package com.example.androidstudyapp.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidstudyapp.data.Category

interface RecipesDao {
    @Query("SELECT * FROM `table - list_recipe`")
    fun getListAllCategory(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategoryToList(category: List<Category>)
}



package com.example.androidstudyapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidstudyapp.data.Category

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM recipeTable")
    suspend fun getListAllCategories(): List<Category>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategoryInList(category: List<Category>?)
}

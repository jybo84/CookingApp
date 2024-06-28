package com.example.androidstudyapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidstudyapp.data.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM recipeTable")
    suspend fun getListCategories(): Flow<List<Category>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategoryInList(category: Category)
}
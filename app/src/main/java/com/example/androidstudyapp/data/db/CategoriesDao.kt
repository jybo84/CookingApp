package com.example.androidstudyapp.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.androidstudyapp.data.Category

@Dao
interface CategoriesDao {
    @Query("Select * from category")
    suspend fun getAllCategories(): List<Category>
    @Upsert
    suspend fun addCategories(categories: List<Category>)
}
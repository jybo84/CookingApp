package com.example.androidstudyapp.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CategoriesDao {
    @Query("Select * from category")
    fun getAllCategories(): List<Category>
    @Upsert
    fun addCategory(category: Category)
}
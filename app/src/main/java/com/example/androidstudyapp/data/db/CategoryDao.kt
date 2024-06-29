package com.example.androidstudyapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidstudyapp.data.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM table_recipe")
    fun getListAllCategory(): List<Category>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategoryToList(category: Category)
}




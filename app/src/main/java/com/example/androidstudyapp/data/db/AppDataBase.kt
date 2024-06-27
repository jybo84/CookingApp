package com.example.androidstudyapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidstudyapp.data.Category

@Database(version = 1, entities = [Category::class])
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCategoriesDao(): CategoriesDao

}
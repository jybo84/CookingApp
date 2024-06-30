package com.example.androidstudyapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidstudyapp.data.Category

@Database(version = 1, entities = [Category::class])
abstract class CategoryDataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoriesDao

    companion object {
        fun getDataBase(context: Context): CategoryDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                CategoryDataBase::class.java,
                "applicationData"
            ).build()
        }
    }
}
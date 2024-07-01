package com.example.androidstudyapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.Recipe

@Database(version = 5, entities = [Category::class, Recipe::class])
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoriesDao

    abstract fun getRecipesDao(): RecipesDao

    companion object {
        fun getDataBase(context: Context): DataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "applicationData"

            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}
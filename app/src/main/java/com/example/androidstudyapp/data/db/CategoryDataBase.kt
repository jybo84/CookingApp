package com.example.androidstudyapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidstudyapp.data.Category

@Database(version = 1, entities = [Category::class])
abstract class RecipeDataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

    companion object {
        fun getDataBase(context: Context): RecipeDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                RecipeDataBase::class.java,
                "applicationData"
            ).build()
        }
    }
}
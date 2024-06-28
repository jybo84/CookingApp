package com.example.androidstudyapp.data.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidstudyapp.data.Category

@Database(entities = [Category::class], version = 1)
abstract class RecipeDb : RoomDatabase() {

    abstract fun getCategoriesDao(): Dao

    companion object {
        fun getDb(context: Context): RecipeDb {
            return Room.databaseBuilder(
                context.applicationContext,
                RecipeDb::class.java,
                "applicationData"
            ).build()
        }
    }
}

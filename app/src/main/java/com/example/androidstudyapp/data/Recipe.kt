package com.example.androidstudyapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table - list_recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
//    @Relation(parentColumn = "id", entityColumn = "ingredients")
    @ColumnInfo(name = "ingredients" )
    val ingredients: List<Ingredient>,
//    @Relation(parentColumn = "id", entityColumn = "method")
    @ColumnInfo(name = "method")
    val method: List<String>,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
) : Parcelable

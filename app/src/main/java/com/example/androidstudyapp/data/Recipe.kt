package com.example.androidstudyapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
@Entity(tableName = "table_recipe")
data class Recipe @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "ingredients")
    val ingredients: List<Ingredient>,
    @ColumnInfo(name = "method")
    val method: List<String>,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
) : Parcelable

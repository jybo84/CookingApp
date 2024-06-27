package com.example.androidstudyapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("category")
data class Category(
    @ColumnInfo("id")
    @PrimaryKey
    var id: Int,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("image_url")
    val imageUrl: String,
) : Parcelable
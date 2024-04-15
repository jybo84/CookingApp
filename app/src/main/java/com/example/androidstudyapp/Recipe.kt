package com.example.androidstudyapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Recipe(
    val id: Int,
    val title: String,
    val ingredients:@RawValue List<Ingredient>,
    val method: List<String>,
    val imageUrl: String
) :  Parcelable

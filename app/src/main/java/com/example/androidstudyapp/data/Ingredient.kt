package com.example.androidstudyapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class Ingredient @Inject constructor(
    val quantity: String,
    val unitOfMeasure: String,
    val description: String,
) : Parcelable

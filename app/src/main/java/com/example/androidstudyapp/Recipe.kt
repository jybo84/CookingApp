package com.example.androidstudyapp

data class Recipe(
    val id: Int,
    val title: String,
    val ingredients: List<Ingredient>,
    val method: List<String>,
    val imageUrl: String
)

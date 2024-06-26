package com.example.androidstudyapp.data

object ImageUtils{

    fun getImageFullUrl(imageUrl: String): String {
        return "$API_BASE_IMAGES_URL/$imageUrl"
    }
}
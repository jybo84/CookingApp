package com.example.androidstudyapp.di

interface Factory<T> {

    fun create(): T
}
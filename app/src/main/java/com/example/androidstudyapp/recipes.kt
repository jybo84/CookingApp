package com.example.androidstudyapp

object STUB {
    private val categories = mutableListOf(
        Category(
            0,
            "Бургеры",
            "burger.png",
            "Рецепты всех популярных видов бургеров"
        ),
        Category(
            1,
            "Десерты",
            "dessert.png",
            "Самые вкусные рецепты десертов специально для вас"
        ),
        Category(
            2,
            "Пицца",
            "pizza.png",
            "Пицца на любой вкус и цвет. Лучшая подборка для тебя"
        ),
        Category(
            3,
            "Рыба",
            "fish.png",
            "Печеная, жареная, сушеная, любая рыба на твой вкус"
        ),
        Category(
            4,
            "Супы",
            "soup.png",
            "От классики до экзотики: мир в одной тарелке"

        ),
        Category(
            5,
            "Салаты",
            "salad.png",
            "Хрустящий калейдоскоп под соусом вдохновения"
        ),
    )

    fun getCategories() = categories
}
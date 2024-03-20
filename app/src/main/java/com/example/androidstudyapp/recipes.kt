package com.example.androidstudyapp

object STUB {
    private val categories = mutableListOf(
        Category(
            0,
            "Бургеры",
            "рецепты всех популярных видов бургеров",
            "assets/burger.png"
        ),
        Category(
            1,
            "Десерты",
            "Самые вкусные рецепты десертов специально для вас",
            "assets/dessert.png"
        ),
        Category(
            2,
            "Пицца",
            "Пицца на любой вкус и цвет. Лучшая подборка для тебя",
            "assets/pizza.png"
        ),
        Category(
            3,
            "Рыба",
            "Печеная, жареная, сушеная, любая рыба на твой вкус",
            "assets/dessert.png"
        ),
        Category(
            4,
            "Супы",
            "От классики до экзотики: мир в одной тарелке",
            "assets/salad.png"
        ),
        Category(
            5,
            "Салаты",
            "Хрустящий калейдоскоп под соусом вдохновения",
            "assets/salad.png"
        ),
    )

    fun getCategories(): List<Category> {
        return categories
    }
}
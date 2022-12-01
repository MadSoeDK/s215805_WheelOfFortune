package com.example.wheeloffortune.data

enum class Category(val words: List<String>) {
    Capitals(listOf("Berlin", "Sydney", "London", "Washington", "Madrid", "Beijing")),
    Countries(listOf("Denmark", "Sweden", "Netherlands", "Switzerland", "Belgium")),
    Fruits(listOf("Grape", "Apple", "Bananas", "Papaya", "Watermelon"))
}
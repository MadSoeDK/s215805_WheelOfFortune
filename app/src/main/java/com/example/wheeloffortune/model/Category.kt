package com.example.wheeloffortune.model

enum class Category(val words: List<String>) {
    Cities(listOf("Paris", "Sydney")),
    BodyParts(listOf("Chest", "Forearm")),
    NoCategory(listOf("")),
}
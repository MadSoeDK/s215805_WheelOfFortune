package com.example.wheeloffortune.model

data class GameState (
    var category: Category = Category.NoCategory,
    var lives: Int = 5,
    var points: Int = 0,
    var hiddenWord: String = "",
    var wordToGuess: String = "",
    var gameMessage: String = "",
    var gameWon: Boolean = false
)
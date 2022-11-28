package com.example.wheeloffortune.data

data class GameState (
    var category: Category?,
    var lives: Int = 5,
    var points: Int = 0,
    var hiddenWord: String = "",
    var wordToGuess: String = "",
    var gameMessage: String = "",
    var gameWon: Boolean = false
)
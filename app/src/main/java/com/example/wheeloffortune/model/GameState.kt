package com.example.wheeloffortune.model

data class GameState (
    var category: Category?,
    var lives: Int = 5,
    var lettersUsed: String = "",
    var points: Int = 0,
    var guessedWord: String = "",
    var word: String = "",
    var msg: String = "",
)
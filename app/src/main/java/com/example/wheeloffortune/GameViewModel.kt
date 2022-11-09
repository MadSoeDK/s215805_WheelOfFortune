package com.example.wheeloffortune

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.wheeloffortune.model.Category
import kotlin.random.Random

class GameViewModel : ViewModel() {

    var gameWon by mutableStateOf(false)
    var keyboard by mutableStateOf(false)
    var category by mutableStateOf(Category.NoCategory)
    var lives by mutableStateOf(5)
    var points by mutableStateOf(0)
    var pointMultiplier by mutableStateOf(0)
    var lettersUsed by mutableStateOf("")
    var guessedWord by mutableStateOf("")
    var wordToGuess by mutableStateOf("")
    var gameMessage by mutableStateOf("")
    var isSpinning by mutableStateOf(false)
    val events = listOf (
        "Extra life", "Miss turn", "Bankrupt", "25", "50", "100", "500", "1500"
    )

    init {
        startGame()
    }

    private fun startGame() {
        category = randomCategory()
        wordToGuess = category.words.random()

        val sb = StringBuilder()
        wordToGuess.forEach {
            sb.append("_")
        }
        guessedWord = sb.toString()
    }

    fun onLetterClick(letter: Char) {
        guessLetter(letter)
        keyboard = false
    }

    private fun guessLetter (guessedLetter: Char) {
        lettersUsed += guessedLetter
        val occurrences: Int

        if(wordToGuess.lowercase().contains(guessedLetter.lowercase())) {
            showLetter(guessedLetter)
            occurrences = wordToGuess.count{ it.uppercase() == guessedLetter.toString() }
            points += pointMultiplier * occurrences
            gameMessage = "$occurrences occurrences. You gain " + pointMultiplier * occurrences + " points!"
        } else {
            lives--
            gameMessage = "Incorrect guess. You loose a life!"
        }
        pointMultiplier = 0

        if(wordToGuess.lowercase() == guessedWord.lowercase()) {
            gameWon = true
        }
    }

    private fun showLetter(letter: Char) {
        wordToGuess.forEachIndexed { index, it ->
            if(it.lowercase() == letter.lowercase()) {
                guessedWord = guessedWord.replaceRange(index,index+1,letter.toString())
            }
        }
    }

    fun handleSpinResult(resultIndex: Int) {
        isSpinning = false

        when (events[resultIndex]) {
            events[0] -> {
                lives++
                gameMessage = "You gain a life! Spin again"
            }
            events[1] -> {
                lives--
                gameMessage = "Miss turn! \nYou loose a life! Spin again"
            }
            events[2] -> {
                points = 0
                gameMessage = "Bankrupt!\nYou loose all points! Spin again"
            }
            events[3] -> {
                pointMultiplier += 25
                gameMessage = "25!\nEarn points on occurrences."
            }
            events[4] -> {
                pointMultiplier += 50
                gameMessage = "50!\nEarn points on occurrences."
            }
            events[5] -> {
                pointMultiplier += 100
                gameMessage = "100!\nEarn points on occurrences."
            }
            events[6] -> {
                pointMultiplier += 500
                gameMessage = "500!\nEarn points on occurrences."
            }
            events[7] -> {
                pointMultiplier += 1500
                gameMessage = "1500!\nEarn points on occurrences."
            }
        }
        keyboard = resultIndex > 2
    }

    fun playAgain() {
        gameWon = false
        keyboard = false
        category = Category.NoCategory
        lives = 5
        points = 0
        pointMultiplier = 0
        lettersUsed = ""
        guessedWord = ""
        wordToGuess = ""
        gameMessage =""
        startGame()
    }

    private fun randomCategory(): Category {
        // TODO: Random not truly random
        return Category.values()[Random.nextInt(Category.values().size - 1)]
    }
}
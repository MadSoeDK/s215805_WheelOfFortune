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
            occurrences = wordToGuess.count{ it.uppercase() == guessedLetter.toString() }//wordToGuess.uppercase().contains(guessedLetter) }
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

    fun spinWheel() {
        keyboard = true
        when (Random.nextInt(0,8)) {
            0 -> {
                lives++
                gameMessage = "You gain a life! Spin again"
                keyboard = false
            }
            1 -> {
                lives--
                gameMessage = "Miss Turn! \nYou loose a life! Spin again"
                keyboard = false
            }
            2 -> {
                points = 0
                gameMessage = "Bankrupt!\nYou loose all points! Spin again"
                keyboard = false
            }
            3 -> {
                pointMultiplier += 25
                gameMessage = "25!\nEarn points on occurrences."
            }
            4 -> {
                pointMultiplier += 50
                gameMessage = "50!\nEarn points on occurrences."
            }
            5 -> {
                pointMultiplier += 100
                gameMessage = "100!\nEarn points on occurrences."
            }
            6 -> {
                pointMultiplier += 1000
                gameMessage = "1000!\nEarn points on occurrences."
            }
            7 -> {
                pointMultiplier += 1500
                gameMessage = "1500!\nEarn points on occurrences."
            }
            8 -> {
                pointMultiplier += 2000
                gameMessage = "2000!\nEarn points on occurrences."
            }
        }
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
        return Category.values()[Random.nextInt(Category.values().size - 1)]
    }
}
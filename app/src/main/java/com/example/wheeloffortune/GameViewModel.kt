package com.example.wheeloffortune

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.wheeloffortune.model.Category
import kotlin.random.Random
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(
    private val navController: NavController
) : ViewModel() {

    var lettersUsed by mutableStateOf("")
    var showKeyboard by mutableStateOf(false)
    var category by mutableStateOf(Category.NoCategory)
    var lives by mutableStateOf(5)
    var points by mutableStateOf(0)
    var spinResult by mutableStateOf(0)

    var hiddenWord by mutableStateOf("")
    var wordToGuess by mutableStateOf("")
    var gameMessage by mutableStateOf("")
    var isSpinning by mutableStateOf(false)
    val events = listOf (
        "Extra life", "Miss turn", "Bankrupt", "25", "50", "100", "500", "1500"
    )

    init {
        startGame()
    }

    /**
     * Start the game by choosing a random category and word, and build the string for the hidden word
     */
    private fun startGame() {
        // TODO: Random not truly random
        category = Category.values()[Random.nextInt(Category.values().size - 1)]
        wordToGuess = category.words.random()

        val sb = StringBuilder()
        wordToGuess.forEach {
            sb.append("_")
        }
        hiddenWord = sb.toString()
    }

    /**
     * OnClickHandler for each letter on the keyboard
     */
    fun onLetterClick(letter: Char) {
        guessLetter(letter)
        showKeyboard = false
    }

    /**
     * Check if the letter pressed on the keyboard, have occurrences in the word to guess
     */
    private fun guessLetter (guessedLetter: Char) {
        lettersUsed += guessedLetter
        var occurrences = 0

        // For each char in wordToGuess, check if it is equal to the guessedLetter.
        // Then replace the "_" with the guessedLetter and increment the occurence
        wordToGuess.forEachIndexed { index, it ->
            if(it.lowercase() == guessedLetter.lowercase()) {
                hiddenWord = hiddenWord.replaceRange(index,index+1,guessedLetter.toString())
                occurrences++;
            }
        }

        // If there is more than 1 occurence, then the guess is correct
        if(occurrences >= 1) {
            points += spinResult * occurrences
            gameMessage = "$occurrences occurrences. You gain " + spinResult * occurrences + " points!"
        } else {
            // If 0 occurences then the guess is incorrect, and the player looses a life.
            lives--
            gameMessage = "Incorrect guess. You loose a life!"
        }

        spinResult = 0

        if(lives <= 0) {
            gameLost()
        }

        if(wordToGuess.lowercase() == hiddenWord.lowercase()) {
            gameWon()
        }
    }

    /**
     * Handles the resultIndex returned from the spinnerWheel, and decides the corresponding message
     */
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
                spinResult += 25
                gameMessage = "25!\nEarn points on occurrences."
            }
            events[4] -> {
                spinResult += 50
                gameMessage = "50!\nEarn points on occurrences."
            }
            events[5] -> {
                spinResult += 100
                gameMessage = "100!\nEarn points on occurrences."
            }
            events[6] -> {
                spinResult += 500
                gameMessage = "500!\nEarn points on occurrences."
            }
            events[7] -> {
                spinResult += 1500
                gameMessage = "1500!\nEarn points on occurrences."
            }
        }
        showKeyboard = resultIndex > 2
    }

    private fun gameWon() {
        viewModelScope.launch {
            delay(2000)
            navController.navigate("WinScreen")
        }
    }

    private fun gameLost() {
        viewModelScope.launch {
            delay(2000)
            navController.navigate("LoseScreen")
        }
    }

    fun beginGame() {
        navController.navigate("GameScreen")
    }

    /**
     * Resets the game state
     */
    fun playAgain() {
        navController.navigate("GameScreen")
        lettersUsed = ""
        showKeyboard = false
        isSpinning = false
        lives = 5
        points = 0
        spinResult = 0
        gameMessage = ""
        viewModelScope.launch {
            delay(200)
            startGame()
        }
    }
}
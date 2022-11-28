package com.example.wheeloffortune

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.wheeloffortune.model.Category
import kotlin.random.Random
import androidx.lifecycle.viewModelScope
import com.example.wheeloffortune.model.GameState
import com.example.wheeloffortune.view.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Math.random
import java.util.*

class GameViewModel(
    private val navController: NavController
) : ViewModel() {

    // State of the game
    private val _uiState = MutableStateFlow( GameState(null) )
    val uiState: StateFlow<GameState> = _uiState.asStateFlow()

    // Used for UI as spin wheel and keyboard
    var showKeyboard by mutableStateOf(false)
    var isSpinning by mutableStateOf(false)
    var spinResult by mutableStateOf(0)

    // Keep used set of used letters to disable those on the keyboard
    var usedLetters: MutableSet<String> = mutableSetOf()

    // The event
    val events = listOf(0, 25, 50, 100, 500, 750, 1000, 1500)

    /**
     * Start the game by choosing a random category and word, 
     * build the string for the hidden word, and initialize state
     */
    private fun startGame() {
        // Reset the UI. Used when game is starting over.
        showKeyboard = false
        isSpinning = false
        usedLetters.clear()
        val category = Category.values().random(Random(Date().time))
        val word = category.words.random(Random(Date().time))

        // Build a string of "___" to represent the hidden word 
        val sb = StringBuilder()
        word.forEach {
            sb.append("_")
        }

        _uiState.update {
            it.copy(
                category = category,
                wordToGuess = word,
                hiddenWord = sb.toString(),
                lives = 1,
                gameMessage = "",
                points = 0,
                gameWon = false
            )
        }
    }

    /**
     * OnClickHandler for each letter on the keyboard
     */
    fun checkUserGuess(letter: Char) {
        usedLetters.add(letter.toString())
        var occurrences = 0
        var hiddenWord = _uiState.value.hiddenWord
        var points = _uiState.value.points
        var lives = _uiState.value.lives

        // For each char in wordToGuess, replace the letter guess in the hidden string, 
        // if the guess and char is correct. This will update the hidden word at the correct index.
        _uiState.value.wordToGuess.forEachIndexed { index, char ->
            if(char.lowercase() == letter.lowercase()) {
                hiddenWord = hiddenWord.replaceRange(index,index+1, letter.toString())
                occurrences++
            }
        }

        val gameMessage: String

        // If there is more than 1 occurence, then the guess is correct
        if(occurrences >= 1) {
            points += spinResult * occurrences
            gameMessage = "$occurrences occurrences. You gain " + spinResult * occurrences + " points!"
        } else {
            // If 0 occurences then the guess is incorrect, and the player looses a life.
            lives--
            gameMessage = "Incorrect guess. You loose a life!"
        }

        // Update the state
        _uiState.update {
            it.copy(
                hiddenWord = hiddenWord,
                points = points,
                lives = lives,
                gameMessage = gameMessage,
            )
        }

        // Reset, ready to spin again
        spinResult = 0

        // If player has won or lost
        if(_uiState.value.lives <= 0 || _uiState.value.wordToGuess.lowercase() == hiddenWord.lowercase()) {
            // If word is correctly guessed, set gameWon
            if (_uiState.value.wordToGuess.lowercase() == hiddenWord.lowercase())
                _uiState.update { it.copy(gameWon = true) }
            // Navigate to the game over screen
            navController.navigate(Screen.StartOver.route)
        }
        showKeyboard = false
    }

    /**
     * Handles the resultIndex returned from the spinnerWheel, and decides the corresponding message
     */
    fun handleSpinResult(resultIndex: Int) {
        isSpinning = false
        var points = _uiState.value.points
        val gameMessage: String

        // If bankrupt
        if (events[resultIndex] == 0) {
            points = 0
            gameMessage = "Bankrupt!\nYou loose all points! Spin again"
        } else {
            spinResult += events[resultIndex]
            gameMessage = "Earn $spinResult points on every occurrence."
        }

        showKeyboard = resultIndex > 0
        _uiState.update {
            it.copy(
                gameMessage = gameMessage,
                points = points,
            )
        }
    }

    // For starting the game from the start screen
    fun beginGame() {
        startGame()
        navController.navigate(Screen.GameScreen.route)
    }

    // For starting the game from the gave over screen
    fun playAgain() {
        startGame()
        navController.navigate(Screen.GameScreen.route)
    }
}
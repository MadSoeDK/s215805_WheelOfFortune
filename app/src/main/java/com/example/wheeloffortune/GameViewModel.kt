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

    private val _uiState = MutableStateFlow( GameState() )
    val uiState: StateFlow<GameState> = _uiState.asStateFlow()

    var showKeyboard by mutableStateOf(false)
    var isSpinning by mutableStateOf(false)
    var spinResult by mutableStateOf(0)

    var usedLetters: MutableSet<String> = mutableSetOf()

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
        val category = Category.values().random()
        val word = "ABCD"//category.words.random()

        val sb = StringBuilder()
        word.forEach {
            sb.append("_")
        }
        _uiState.value = GameState(
            category = category,
            wordToGuess = word,
            hiddenWord = sb.toString(),
            lives = 1
        )
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
        var gameMessage = _uiState.value.gameMessage

        _uiState.value.wordToGuess.forEachIndexed { index, it ->
            if(it.lowercase() == letter.lowercase()) {
                hiddenWord = hiddenWord.replaceRange(index,index+1, letter.toString())
                occurrences++
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

        _uiState.update {
            it.copy(
                hiddenWord = hiddenWord,
                points = points,
                lives = lives,
                gameMessage = gameMessage,
            )
        }

        spinResult = 0

        // If player has won or lost
        if(_uiState.value.lives <= 0 || _uiState.value.wordToGuess.lowercase() == hiddenWord.lowercase()) {
            if (_uiState.value.wordToGuess.lowercase() == hiddenWord.lowercase())
                _uiState.update { it.copy(gameWon = true) }
           gameOver()
        }
        showKeyboard = false
    }

    /**
     * Handles the resultIndex returned from the spinnerWheel, and decides the corresponding message
     */
    fun handleSpinResult(resultIndex: Int) {
        isSpinning = false
        var lives = _uiState.value.lives
        var gameMessage = _uiState.value.gameMessage

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
                _uiState.update { it.copy(points = 0) }
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
        _uiState.update {
            it.copy(
                lives = lives,
                gameMessage = gameMessage,
            )
        }
    }

    private fun gameOver() {
        viewModelScope.launch {
            delay(1000)
            navController.navigate(Screen.StartOver.route)
        }
    }

    fun beginGame() {
        navController.navigate(Screen.GameScreen.route)
    }

    /**
     * Resets the game state
     */
    fun playAgain() {
        navController.navigate(Screen.GameScreen.route)
        showKeyboard = false
        isSpinning = false
        usedLetters.clear()
        //_uiState.value = GameState()
        viewModelScope.launch {
            delay(200)
            startGame()
        }
    }
}
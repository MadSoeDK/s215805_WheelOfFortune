package com.example.wheeloffortune

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wheeloffortune.model.Category
import com.example.wheeloffortune.model.GameState
import kotlin.random.Random

class GameViewModel : ViewModel() {

    var keyboard by mutableStateOf(true)
    var gameState by mutableStateOf(GameState(null))
    var spinResult = 0

    fun onLetterClick(letter: Char) {
        //guessedLetter = letter.toString();
        guessLetter(letter)
        //keyboard = false
    }

    fun getGuessedWord(): String {
        return gameState.guessedWord
    }

    init {
        gameState.category = Category.Cities
        gameState.word = gameState.category!!.words.random()

        val sb = StringBuilder()
        gameState.word.forEach {
            sb.append("_")
        }
        gameState.guessedWord = sb.toString()
    }

    private fun guessLetter (guessedLetter: Char) {
        gameState.lettersUsed += guessedLetter

        if(gameState.word.lowercase().contains(guessedLetter.lowercase())) {
            showLetter(guessedLetter)
            gameState.points += spinResult
        } else {
            gameState.lives--
        }
        spinResult = 0
    }

    private fun showLetter(letter: Char) {
        gameState.word.forEachIndexed { index, it ->
            if(it.lowercase() == letter.lowercase()) {
                gameState.guessedWord = gameState.guessedWord.replaceRange(index,index+1,letter.toString())
            }
        }
    }

    fun spinWheel() {
        when (Random.nextInt(0,8)) {
            0 -> {
                gameState.lives++
                gameState.msg = "Extra turn! \nYou gain a life!"
            }
            1 -> {
                gameState.lives--
                gameState.msg = "Miss Turn! \nYou loose a life!"
            }
            2 -> {
                gameState.points = 0
                gameState.msg = "Bankrupt!\nYou loose all points!"
            }
            3 -> {
                spinResult += 25
                gameState.msg = "25!\nEarn points on occurrences."
            }
            4 -> {
                spinResult += 50
                gameState.msg = "50!\nEarn points on occurrences."
            }
            5 -> {
                spinResult += 100
                gameState.msg = "100!\nEarn points on occurrences."
            }
            6 -> {
                spinResult += 1000
                gameState.msg = "1000!\nEarn points on occurrences."
            }
            7 -> {
                spinResult += 1500
                gameState.msg = "1500!\nEarn points on occurrences."
            }
            8 -> {
                spinResult += 2000
                gameState.msg = "2000!\nEarn points on occurrences."
            }
        }
    }
}
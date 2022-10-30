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

    fun onLetterClick() {
        keyboard = false
        println(keyboard)
    }
}
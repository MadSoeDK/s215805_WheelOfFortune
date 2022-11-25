package com.example.wheeloffortune.view

sealed class Screen(val route: String) {
    object StartScreen : Screen("StartScreen")
    object GameScreen : Screen("GameScreen")
    object StartOver : Screen("StartOver")
}
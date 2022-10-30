package com.example.wheeloffortune.view

sealed class Screen(val route: String) {
    object GameScreen : Screen("GameScreen")
}
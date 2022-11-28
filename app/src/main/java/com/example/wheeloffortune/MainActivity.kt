package com.example.wheeloffortune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wheeloffortune.ui.theme.WheelOfFortuneTheme
import com.example.wheeloffortune.view.*
import com.example.wheeloffortune.view.screens.GameScreen
import com.example.wheeloffortune.view.screens.NewGameScreen
import com.example.wheeloffortune.view.screens.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WheelOfFortuneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val gameViewModel = remember { GameViewModel(navController) }
                    NavHost(
                        modifier = Modifier,
                        navController = navController,
                        startDestination = Screen.StartScreen.route
                    ) {
                        composable(Screen.GameScreen.route) {
                            GameScreen(gameViewModel)
                        }
                        composable(Screen.StartOver.route) {
                            NewGameScreen(gameViewModel)
                        }
                        composable(Screen.StartScreen.route) {
                            StartScreen(gameViewModel)
                        }
                    }
                }
            }
        }
    }
}

// Screen routes
sealed class Screen(val route: String) {
    object StartScreen : Screen("StartScreen")
    object GameScreen : Screen("GameScreen")
    object StartOver : Screen("StartOver")
}
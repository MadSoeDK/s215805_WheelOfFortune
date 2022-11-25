package com.example.wheeloffortune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wheeloffortune.ui.theme.WheelOfFortuneTheme
import com.example.wheeloffortune.view.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WheelOfFortuneTheme {
                // A surface container using the 'background' color from the theme
                // TODO: Dark mode...
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val gameViewModel = GameViewModel(navController)
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
package com.example.wheeloffortune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wheeloffortune.ui.theme.WheelOfFortuneTheme
import com.example.wheeloffortune.view.GameScreen
import com.example.wheeloffortune.view.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WheelOfFortuneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    NavHost(
                        modifier = Modifier,
                        navController = navController,
                        startDestination = Screen.GameScreen.route
                    ) {
                        composable(Screen.GameScreen.route) {
                            // TODO: Dark mode...
                            GameScreen(GameViewModel(navController))
                        }
                    }
                }
            }
        }
    }
}
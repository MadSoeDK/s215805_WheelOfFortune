package com.example.wheeloffortune.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wheeloffortune.GameViewModel

@Composable
fun LoseScreen(
    viewModel: GameViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(0.dp, 30.dp).fillMaxWidth()
    ) {
        Text(text = "You Have Lost!", fontSize = 28.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(0.dp, 20.dp))
        Text(text = "The hidden word was " + viewModel.wordToGuess)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { viewModel.playAgain() }) {
            Text(text = "Play again!")
        }
    }
}
package com.example.wheeloffortune.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wheeloffortune.GameViewModel
import com.example.wheeloffortune.view.composables.LetterInput

@Composable
fun GameScreen(
    viewModel: GameViewModel
) {
    Column {
        Text(text = viewModel.gameState.lettersUsed)
        Text(text = viewModel.gameState.lives.toString())
        Text(text = viewModel.gameState.category.toString())
        Text(text = viewModel.gameState.points.toString())
        Text(text = viewModel.gameState.word)
        Text(text = viewModel.gameState.guessedWord)

        Button(onClick = {  } ) {
            Text(text = viewModel.getGuessedWord())
        }

        if(viewModel.keyboard) {
            LetterInput { viewModel.onLetterClick(it) }
        }
        
        Button(onClick = { viewModel.spinWheel() }) {
            Text(text = "Spin the wheel!")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GameScreenPreview() {
    GameScreen(viewModel = viewModel())
}
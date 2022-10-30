package com.example.wheeloffortune.view

import androidx.compose.foundation.layout.Column
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
    viewModel.startGame()
    Column {
        if(viewModel.keyboard) {
            LetterInput { viewModel.onLetterClick() }
        }
    }
}

@Composable
@Preview(showBackground = true, device = "Pixel_3a")
fun GameScreenPreview() {
    GameScreen(viewModel = viewModel())
}
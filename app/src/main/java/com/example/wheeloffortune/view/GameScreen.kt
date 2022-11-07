package com.example.wheeloffortune.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wheeloffortune.GameViewModel
import com.example.wheeloffortune.view.composables.LetterInput

@Composable
fun GameScreen(
    viewModel: GameViewModel
) {
    Column(
        modifier = Modifier
            .padding(10.dp, 30.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        if(!viewModel.gameWon) {
            Column {
                Column(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .padding(0.dp, 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = viewModel.category.toString())
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = viewModel.guessedWord, fontSize = 24.sp, letterSpacing = 5.sp)
                }
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Score: " + viewModel.points.toString())
                        Text(text = "Lives: " + viewModel.lives.toString())
                    }
                    Text(text = "Letters used: " + viewModel.lettersUsed)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = viewModel.gameMessage,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(0.dp, 50.dp),
                    textAlign = TextAlign.Center
                )

                if(viewModel.keyboard) {
                    LetterInput(viewModel.lettersUsed) { viewModel.onLetterClick(it) }
                } else {
                    Button(onClick = { viewModel.spinWheel() }) {
                        Text(text = "Spin the wheel!")
                    }
                }
            }
        } else {
            Button(onClick = { viewModel.playAgain() }) {
                Text(text = "You have won! Play again")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GameScreenPreview() {
    GameScreen(viewModel = viewModel())
}
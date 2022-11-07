package com.example.wheeloffortune.view

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.commandiron.spin_wheel_compose.DefaultSpinWheel
import com.commandiron.spin_wheel_compose.SpinWheelDefaults
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    viewModel: GameViewModel
) {
    Column(
        modifier = Modifier
            .padding(10.dp, 16.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        if (!viewModel.gameWon) {
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = viewModel.category.toString())
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = viewModel.guessedWord, fontSize = 24.sp, letterSpacing = 5.sp)
                }
                //Text(text = "Letters used: " + viewModel.lettersUsed)
            }
            Box (
                contentAlignment = Alignment.BottomCenter
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = viewModel.gameMessage,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    DefaultSpinWheel(
                        dimensions = SpinWheelDefaults.spinWheelDimensions(
                            spinWheelSize = 280.dp
                        ),
                        animationAttr = SpinWheelDefaults.spinWheelAnimationAttr(
                            pieCount = viewModel.events.size,
                            durationMillis = 3000,
                            //delayMillis = 200,
                            //rotationPerSecond = 2f,
                            easing = LinearOutSlowInEasing,
                            //startDegree = 180f,
                        ),
                        isSpinning = viewModel.isSpinning,
                        onFinish = { viewModel.isSpinning = false },
                        resultDegree = viewModel.spinResult
                    ) { pieIndex ->
                        Text(text = viewModel.events[pieIndex])
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    if (!viewModel.keyboard) {
                        val coroutineScope = rememberCoroutineScope()
                        println(viewModel.spinResult)
                        Button(
                            onClick = {
                                viewModel.isSpinning = true
                                coroutineScope.launch {
                                    viewModel.spinWheel()
                                }
                            },
                            content = { Text(text = "Spin the wheel!", fontSize = 18.sp) }
                        )
                    }
                }

                if (viewModel.keyboard) {
                    LetterInput(viewModel.lettersUsed) { viewModel.onLetterClick(it) }
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
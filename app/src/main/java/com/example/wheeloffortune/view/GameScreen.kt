package com.example.wheeloffortune.view

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import kotlin.random.Random

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
                Text(text = viewModel.hiddenWord, fontSize = 24.sp, letterSpacing = 5.sp)
            }
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

                /**
                 * Spin Wheel
                 * https://github.com/commandiron/SpinWheelCompose
                 * @author commandiron
                 */
                DefaultSpinWheel(
                    dimensions = SpinWheelDefaults.spinWheelDimensions(
                        spinWheelSize = 280.dp
                    ),
                    animationAttr = SpinWheelDefaults.spinWheelAnimationAttr(
                        durationMillis = 3500,
                        easing = LinearOutSlowInEasing,
                        autoResetDelay = 0
                    ),
                    isSpinning = viewModel.isSpinning,
                    resultDegree = Random.nextFloat() * 360f,
                    onFinish = { viewModel.handleSpinResult(it) }
                ){ pieIndex ->
                    Text(text = viewModel.events[pieIndex])
                }
                Spacer(modifier = Modifier.height(30.dp))
                if (!viewModel.showKeyboard && !viewModel.isSpinning) {
                    Button(
                        onClick = { viewModel.isSpinning = true },
                        content = { Text(text = "Spin the wheel!", fontSize = 18.sp) }
                    )
                } else {
                    Spacer(modifier = Modifier.height(47.5.dp))
                }
            }

            if (viewModel.showKeyboard) {
                LetterInput(viewModel.lettersUsed) { viewModel.onLetterClick(it) }
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun GameScreenPreview() {
    GameScreen(viewModel = viewModel())
}
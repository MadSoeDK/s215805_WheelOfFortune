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
fun StartScreen(
    viewModel: GameViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Wheel of Fortune", fontSize = 36.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { viewModel.beginGame() }
        ) {
            Text(text = "Start Spinning!")
        }
    }

}
package com.example.wheeloffortune.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LetterButton (
    letter: Char,
    onButtonClick: (Char) -> Unit
) {
    Box(modifier = Modifier.padding(6.dp, 0.dp).clickable { onButtonClick(letter) }) {
        Box(modifier = Modifier.background(Color.Gray)) {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            ) {
                Text(text = letter.toString(), Modifier.width(10.dp))
            }
        }
    }
}
package com.example.wheeloffortune.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LetterInput(
    onClick: (Char) -> Unit
) {
    Column {
        var c = 'A'
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(0.dp, 8.dp)
        ) {
            while (c <= 'I') {
                LetterButton(letter = c) {
                    onClick(c)
                }
                ++c
            }
        }
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(0.dp, 8.dp)
        ) {
            while (c <= 'R') {
                LetterButton(letter = c) {
                    onClick(c)
                }
                ++c
            }
        }
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(0.dp, 8.dp)
        ) {
            while (c <= 'Z') {
                LetterButton(letter = c) {
                    onClick(c)
                }
                ++c
            }
        }
        c = 'A'
    }
}
package com.example.wheeloffortune.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LetterInput(
    lettersUsed: String,
    onClick: (Char) -> Unit,
) {
    LazyVerticalGrid (
        columns = GridCells.Adaptive(50.dp),
        contentPadding = PaddingValues(4.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.background(Color.White).alpha(0.8f)
    ) {
        val alphabets = CharRange('A','Z')
        alphabets.forEach {
            item {
                var enabled = true
                if (lettersUsed.contains(it))
                    enabled = false
                LetterButton(letter = it, enabled = enabled) {
                    onClick(it)
                }
            }
        }
    }
}
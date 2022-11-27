package com.example.wheeloffortune.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LetterBox(
    hiddenWord: String
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(30.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        hiddenWord.forEachIndexed { index, char ->
            item {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(6.dp, 6.dp),
                    contentAlignment = Alignment.Center,

                    ) {
                    Text(
                        text = if(hiddenWord[index].toString() != "_") char.toString().uppercase() else "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
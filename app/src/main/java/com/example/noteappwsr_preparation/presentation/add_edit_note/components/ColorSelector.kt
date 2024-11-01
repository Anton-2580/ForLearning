package com.example.noteappwsr_preparation.presentation.add_edit_note.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.noteappwsr_preparation.data.model.Note


@Composable
fun ColorSelector(
    selectedColorState: MutableIntState,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier.height(80.dp)) {
        items(Note.noteColors) {
            if (selectedColorState.intValue == it.toArgb()) {
                Canvas(Modifier.size(60.dp)) {
                    drawCircle(Color.Black, 60f)
                    drawCircle(it, 55f)
                }
            } else {
                Canvas(modifier = Modifier
                    .size(60.dp)
                    .clickable { selectedColorState.intValue = it.toArgb() }
                ) {
                    drawCircle(color = it, 60f)
                }
            }
        }
    }
}
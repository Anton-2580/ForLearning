package com.example.noteappwsr_preparation.presentation.add_edit_note.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.noteappwsr_preparation.data.model.Note


@Composable
fun ColorSelector(
    selectedColorState: MutableIntState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    LazyRow(modifier = modifier.height(80.dp)) {
        items(Note.noteColors) {
            if (selectedColorState.intValue == it.toArgb()) {
                Box(
                    Modifier
                        .size(60.dp)
                        .drawWithContent {
                            drawCircle(Color.Black, 60f)
                            drawCircle(it, 55f)
                        }
                        .shadow(15.dp, CircleShape)
                )
            } else {
                Box(modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        selectedColorState.intValue = it.toArgb()
                        onClick()
                    }
                    .drawWithContent { drawCircle(color = it, 60f) }
                    .shadow(15.dp, CircleShape)
                )
            }
        }
    }
}
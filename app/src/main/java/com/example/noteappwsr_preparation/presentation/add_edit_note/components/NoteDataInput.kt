package com.example.noteappwsr_preparation.presentation.add_edit_note.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun NoteDataInput(
    value: MutableState<String>,
    hint: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontSize: TextUnit = 30.sp,
    maxSymbols: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE
) {
    BasicTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = { if (it.length <= maxSymbols) value.value = it },
        textStyle = TextStyle(
            fontSize = fontSize,
            color = color
        ),
        maxLines = maxLines,
        decorationBox  = {
            if (value.value.isEmpty())
                Text(text = hint, color = color, fontSize = fontSize)
            else
                it()
        }
    )
}
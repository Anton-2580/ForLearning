package com.example.noteappwsr_preparation.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.noteappwsr_preparation.presentation.NotesEvent
import com.example.noteappwsr_preparation.presentation.NotesViewModel


@Composable
fun CancelDeleteNote(
    viewModel: NotesViewModel,
    isDelete: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isDelete.value,
        enter = fadeIn(), exit = fadeOut()
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row {
                Text(text = "Note was deleted",
                    modifier = Modifier.align(Alignment.CenterVertically))
                Button(
                    contentPadding = ButtonDefaults.TextButtonContentPadding,
                    onClick = {
                        isDelete.value = false
                        viewModel.onEvent(NotesEvent.RestoreNote)
                }) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}
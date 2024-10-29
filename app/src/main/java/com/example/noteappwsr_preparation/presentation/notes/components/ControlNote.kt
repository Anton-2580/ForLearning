package com.example.noteappwsr_preparation.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.noteappwsr_preparation.data.model.Note
import com.example.noteappwsr_preparation.presentation.NotesEvent
import com.example.noteappwsr_preparation.presentation.NotesViewModel
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule


@Composable
fun ControlNote(
    viewModel: NotesViewModel,
    note: Note,
    isDelete: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val timer = Timer()
    var timerTask: TimerTask? = null

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(contentPadding = ButtonDefaults.TextButtonContentPadding,
            onClick = {
                // TODO: Добавить изменение записи
        }) {
            Icon(imageVector = Icons.Default.Create, contentDescription = "Change note")
        }

        Button(contentPadding = ButtonDefaults.TextButtonContentPadding,
            onClick = {
                viewModel.onEvent(NotesEvent.DeleteNote(note))
                isDelete.value = true

                timerTask?.cancel()
                timerTask = timer.schedule(5000) {
                    isDelete.value = false
                }
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete note")
        }
    }
}
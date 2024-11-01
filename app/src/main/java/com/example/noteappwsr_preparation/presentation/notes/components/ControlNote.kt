package com.example.noteappwsr_preparation.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.noteappwsr_preparation.AddEditNoteScreen
import com.example.noteappwsr_preparation.data.model.Note
import com.example.noteappwsr_preparation.presentation.notes.NotesEvent
import com.example.noteappwsr_preparation.presentation.notes.NotesViewModel
import kotlinx.coroutines.launch


@Composable
fun ControlNote(
    viewModel: NotesViewModel,
    navController: NavController,
    note: Note,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(contentPadding = ButtonDefaults.TextButtonContentPadding,
            onClick = {
                navController.navigate(
                    AddEditNoteScreen(noteId = note.id, noteColor = note.color)
                )
        }) {
            Icon(imageVector = Icons.Default.Create, contentDescription = "Change note")
        }

        Button(contentPadding = ButtonDefaults.TextButtonContentPadding,
            onClick = {
                viewModel.onEvent(NotesEvent.DeleteNote(note))
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Note deleted",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NotesEvent.RestoreNote)
                    }
                }
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete note")
        }
    }
}
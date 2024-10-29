package com.example.noteappwsr_preparation.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteappwsr_preparation.presentation.notes.components.CancelDeleteNote
import com.example.noteappwsr_preparation.presentation.notes.components.GridNotes
import com.example.noteappwsr_preparation.presentation.notes.components.OrderMenu
import com.example.noteappwsr_preparation.presentation.notes.components.SortingNotes


@SuppressLint("UnrememberedMutableState")
@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel()
) {
    val noteState by viewModel.state
    val selectedOrderIdState = mutableIntStateOf(1)
    val selectedOrderTypeIdState = mutableIntStateOf(1)
//    viewModel.addNote(Note(1, "a;sf;", "a;sf;".repeat(200), System.currentTimeMillis() / 1000, 20))
//    viewModel.addNote(Note(2, "Список дел", "приготовить покушать", System.currentTimeMillis() / 1000, 20))
//    viewModel.addNote(Note(3, "Задача номер 1", "доделать приложение", System.currentTimeMillis() / 1000, 20))

    val isDelete = mutableStateOf(false)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Переход на другой экран */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OrderMenu {
                viewModel.onEvent(NotesEvent.ToggleOrderSection)
            }

            AnimatedVisibility(visible = noteState.isOrderSectionVisible,) {
                SortingNotes(
                    selectedOrderIdState,
                    selectedOrderTypeIdState
                ) { order -> viewModel.onEvent(NotesEvent.Order(order)) }
            }

            GridNotes(viewModel, isDelete)

            CancelDeleteNote(viewModel, isDelete)
        }
    }
}
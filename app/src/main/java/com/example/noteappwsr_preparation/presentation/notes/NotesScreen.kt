package com.example.noteappwsr_preparation.presentation.notes

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteappwsr_preparation.AddEditNoteScreen
import com.example.noteappwsr_preparation.presentation.notes.components.OrderMenu
import com.example.noteappwsr_preparation.presentation.notes.components.SortingNotes
import com.example.noteappwsr_preparation.presentation.notes.components.GridNotes


@SuppressLint("UnrememberedMutableState")
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val noteState by viewModel.state
    val selectedOrderIdState = mutableIntStateOf(1)
    val selectedOrderTypeIdState = mutableIntStateOf(1)

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(
                    AddEditNoteScreen(
                        noteId = null,
                        noteColor = -1
                    )
                )
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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

            GridNotes(viewModel, navController, snackbarHostState)
        }
    }
}
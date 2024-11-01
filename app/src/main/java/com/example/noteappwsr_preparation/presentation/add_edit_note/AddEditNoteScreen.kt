package com.example.noteappwsr_preparation.presentation.add_edit_note

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteappwsr_preparation.NotesScreen
import com.example.noteappwsr_preparation.data.model.Note
import com.example.noteappwsr_preparation.presentation.add_edit_note.components.ColorSelector
import com.example.noteappwsr_preparation.presentation.add_edit_note.components.NoteDataInput
import com.example.noteappwsr_preparation.presentation.notes.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random


@SuppressLint("UnrememberedMutableState")
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val selectedColorState = remember { mutableIntStateOf(
        if (noteColor == -1)
            Note.noteColors[Random.nextInt(0, Note.noteColors.lastIndex)].toArgb()
        else
            noteColor
    ) }

    val animateColor = remember { Animatable(
        Color(selectedColorState.intValue)
    ) }

    val title = remember { mutableStateOf(viewModel.state.value?.title.orEmpty()) }
    val content = remember { mutableStateOf(viewModel.state.value?.content.orEmpty()) }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                UiEvent.SaveNote -> navController.navigate(NotesScreen)
                is UiEvent.ShowSnackbar ->
                    snackbarHostState.showSnackbar(message = it.message)

                UiEvent.GetNote -> {
                    title.value = viewModel.state.value?.title.orEmpty()
                    content.value = viewModel.state.value?.content.orEmpty()
                }
            }
        }
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Surface(color = animateColor.value) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Button(onClick = { navController.navigate(NotesScreen) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "go back")
                }

                ColorSelector(selectedColorState = selectedColorState) {
                    scope.launch {
                        animateColor.animateTo(
                            targetValue = Color(selectedColorState.intValue),
                            animationSpec = tween(
                                durationMillis = 500
                            )
                        )
                    }
                }

                NoteDataInput(title, "Title", Modifier.padding(10.dp), maxSymbols = 20, maxLines = 1)
                NoteDataInput(content, "Content",
                    Modifier
                        .padding(10.dp)
                        .weight(1f))

                Button(onClick = {
                    viewModel.addEditNote(Note(
                        viewModel.state.value?.id,
                        title.value,
                        content.value,
                        System.currentTimeMillis() / 1000,
                        selectedColorState.intValue)
                    )
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
}
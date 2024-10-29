package com.example.noteappwsr_preparation.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.noteappwsr_preparation.data.model.Note
import com.example.noteappwsr_preparation.domain.use_case.NoteUseCases
import com.example.noteappwsr_preparation.domain.util.NoteOrder
import com.example.noteappwsr_preparation.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// MVVM - Model - View - ViewModel
// Jetpack compose
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : BaseViewModel() {
    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var getNotesJob: Job? = null

    private var recentlyDeletedNote: Note? = null

    init {
        getNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                handleError {
                    recentlyDeletedNote = event.note
                    noteUseCases.deleteNote(event.note)
                }
            }

            is NotesEvent.Order -> {
                getNotes(event.noteOrder)
            }

            NotesEvent.RestoreNote -> {
                handleError {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@handleError)
                    recentlyDeletedNote = null
                }
            }

            NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }

    fun addNote(note: Note) {
        handleError {
            noteUseCases.addNote(note)
        }
    }
}
package com.example.noteappwsr_preparation.presentation.add_edit_note

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.noteappwsr_preparation.AddEditNoteScreen
import com.example.noteappwsr_preparation.data.model.Note
import com.example.noteappwsr_preparation.domain.use_case.NoteUseCases
import com.example.noteappwsr_preparation.presentation.BaseViewModel
import com.example.noteappwsr_preparation.presentation.notes.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {
    private var _state: MutableState<Note?> = mutableStateOf(null)
    val state: State<Note?> = _state

    fun addEditNote(note: Note) {
        handleError {
            noteUseCases.addNote(note)
            _eventFlow.emit(UiEvent.SaveNote)
        }
    }

    init {
        savedStateHandle.toRoute<AddEditNoteScreen>().let {
            if (it.noteId !== null && it.noteId != -1) {
                getNoteById(it.noteId)
            }
        }
    }

    fun getNoteById(noteId: Int) {
        viewModelScope.launch {
            _state.value = noteUseCases.getNoteById(noteId)
            _eventFlow.emit(UiEvent.GetNote)
        }
    }
}

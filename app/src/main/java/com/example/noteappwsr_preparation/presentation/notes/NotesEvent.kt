package com.example.noteappwsr_preparation.presentation.notes

import com.example.noteappwsr_preparation.data.model.Note
import com.example.noteappwsr_preparation.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
package com.example.noteappwsr_preparation.presentation.notes

import com.example.noteappwsr_preparation.data.model.Note
import com.example.noteappwsr_preparation.domain.util.NoteOrder
import com.example.noteappwsr_preparation.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)

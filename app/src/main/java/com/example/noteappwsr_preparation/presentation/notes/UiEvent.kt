package com.example.noteappwsr_preparation.presentation.notes

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
    object SaveNote: UiEvent()
    object GetNote: UiEvent()
}
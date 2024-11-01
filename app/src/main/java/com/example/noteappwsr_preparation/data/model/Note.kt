package com.example.noteappwsr_preparation.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappwsr_preparation.ui.theme.BabyBlue
import com.example.noteappwsr_preparation.ui.theme.LightGreen
import com.example.noteappwsr_preparation.ui.theme.RedOrange
import com.example.noteappwsr_preparation.ui.theme.RedPink
import com.example.noteappwsr_preparation.ui.theme.Violet

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)

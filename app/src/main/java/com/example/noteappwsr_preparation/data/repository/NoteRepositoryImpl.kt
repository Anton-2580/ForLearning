package com.example.noteappwsr_preparation.data.repository


import com.example.noteappwsr_preparation.data.db_source.NoteDao
import com.example.noteappwsr_preparation.data.model.Note
import com.example.noteappwsr_preparation.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)

    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}
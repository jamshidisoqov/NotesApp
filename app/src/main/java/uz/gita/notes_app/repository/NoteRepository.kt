package uz.gita.notes_app.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/6/2022
interface NoteRepository {

    suspend fun insertNote(noteData: NoteData)

    suspend fun updateNote(noteData: NoteData)

    suspend fun deleteNote(noteData: NoteData)

    suspend fun deleteAllTrashNotes()

    suspend fun deleteNoteCategory(noteCategoryData: NoteCategoryData)

    suspend fun insertNoteCategory(noteCategoryData: NoteCategoryData)

    fun getAllNoteByCategory(category: Int): Flow<List<NoteData>>

    fun getAllNoteCategory(): Flow<List<NoteCategoryData>>

    fun getAllTrash(): Flow<List<NoteData>>

    fun searchNotes(query: String): Flow<List<NoteData>>

    fun getAllTags(): Flow<List<String>>


}
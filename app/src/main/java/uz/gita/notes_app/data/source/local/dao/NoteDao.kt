package uz.gita.notes_app.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.source.local.entity.NoteCategoryEntity
import uz.gita.notes_app.data.source.local.entity.NoteEntity

// Created by Jamshid Isoqov an 9/6/2022
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteCategory(noteCategoryEntity: NoteCategoryEntity)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Update
    suspend fun updateNoteCategory(noteCategoryEntity: NoteCategoryEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteList: List<NoteEntity>)

    @Query("SELECT*FROM notes WHERE category=:category")
    fun getAllNotesByCategory(category: Int = 1): Flow<List<NoteEntity>>

    @Query(" SELECT*FROM note_category")
    fun getAllNotesCategory(): Flow<List<NoteCategoryEntity>>


}
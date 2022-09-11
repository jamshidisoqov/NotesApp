package uz.gita.notes_app.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteCategoryData
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

    @Delete
    suspend fun deleteNoteCategory(noteCategoryEntity: NoteCategoryEntity)

    @Query("SELECT*FROM notes WHERE category=:category AND status=1")
    fun getAllNotesByCategory(category: Int = 1): Flow<List<NoteEntity>>

    @Query(" SELECT*FROM note_category")
    fun getAllNotesCategory(): Flow<List<NoteCategoryEntity>>

    @Query("SELECT*FROM notes WHERE title LIKE '%' || :query || '%' AND status = 1 OR tag LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<NoteEntity>>

    @Query("SELECT*FROM notes WHERE status = 2")
    fun getAllTrashes(): Flow<List<NoteEntity>>

    @Query("SELECT*FROM notes WHERE status = 1")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE status = 2")
    suspend fun deleteAllTrashNotes()

    @Query("SELECT tag FROM notes WHERE status = 1 AND NOT(tag isNull or tag ='')")
    fun getAllTags(): Flow<List<String>>

}
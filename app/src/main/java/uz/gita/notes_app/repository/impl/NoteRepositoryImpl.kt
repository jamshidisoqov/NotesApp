package uz.gita.notes_app.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.data.source.local.AppDatabase
import uz.gita.notes_app.repository.NoteRepository

class NoteRepositoryImpl private constructor() : NoteRepository {

    private val dao = AppDatabase.getInstance().noteDao()

    override suspend fun insertNote(noteData: NoteData) = dao.insertNote(noteData.toNoteEntity())

    override suspend fun updateNote(noteData: NoteData) = dao.updateNote(noteData.toNoteEntity())

    override suspend fun deleteNote(noteData: NoteData) = dao.deleteNote(noteData.toNoteEntity())

    override suspend fun insertNoteCategory(noteCategoryData: NoteCategoryData) =
        dao.insertNoteCategory(noteCategoryData.toNoteCategoryEntity())

    override fun getAllNoteByCategory(category: Int): Flow<List<NoteData>> =
        dao.getAllNotesByCategory(category).map { noteList ->
            noteList.map { noteEntity ->
                noteEntity.toNoteData()
            }
        }.flowOn(Dispatchers.IO)

    override fun getAllNoteCategory(): Flow<List<NoteCategoryData>> =
        dao.getAllNotesCategory().map { noteCategoryList ->
            noteCategoryList.map { noteCategoryEntity ->
                noteCategoryEntity.toNoteCategoryData()
            }
        }.flowOn(Dispatchers.IO)

    companion object {
        private lateinit var instance: NoteRepository
        fun getInstance(): NoteRepository {
            if (!Companion::instance.isInitialized) {
                instance = NoteRepositoryImpl()
            }
            return instance
        }
    }
}
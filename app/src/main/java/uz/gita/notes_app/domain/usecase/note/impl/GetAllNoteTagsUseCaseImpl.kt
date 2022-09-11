package uz.gita.notes_app.domain.usecase.note.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.domain.usecase.note.GetAllNoteTagsUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class GetAllNoteTagsUseCaseImpl : GetAllNoteTagsUseCase {
    val repository = NoteRepositoryImpl.getInstance()
    override fun getAllNoteTags(): Flow<List<String>>  = repository.getAllTags()
}
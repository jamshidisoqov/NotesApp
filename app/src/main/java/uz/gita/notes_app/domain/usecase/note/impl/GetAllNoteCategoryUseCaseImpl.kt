package uz.gita.notes_app.domain.usecase.note.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.domain.usecase.note.GetAllNoteCategoryUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class GetAllNoteCategoryUseCaseImpl : GetAllNoteCategoryUseCase {

    private val repository = NoteRepositoryImpl.getInstance()
    override fun getAllNoteCategory(): Flow<List<NoteCategoryData>> =
        repository.getAllNoteCategory()
}
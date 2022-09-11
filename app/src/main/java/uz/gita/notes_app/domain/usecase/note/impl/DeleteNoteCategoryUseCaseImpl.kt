package uz.gita.notes_app.domain.usecase.note.impl

import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.domain.usecase.note.DeleteNoteCategoryUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class DeleteNoteCategoryUseCaseImpl : DeleteNoteCategoryUseCase {
    private val repository = NoteRepositoryImpl.getInstance()
    override suspend fun deleteNoteCategory(noteCategoryData: NoteCategoryData) =
        repository.deleteNoteCategory(noteCategoryData)
}
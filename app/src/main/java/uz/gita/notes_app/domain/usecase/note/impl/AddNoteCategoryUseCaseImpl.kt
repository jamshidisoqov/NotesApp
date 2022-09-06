package uz.gita.notes_app.domain.usecase.note.impl

import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.domain.usecase.note.AddNoteCategoryUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class AddNoteCategoryUseCaseImpl : AddNoteCategoryUseCase {
    private val noteRepository = NoteRepositoryImpl.getInstance()
    override suspend fun addNoteCategory(categoryData: NoteCategoryData) =
        noteRepository.insertNoteCategory(categoryData)
}
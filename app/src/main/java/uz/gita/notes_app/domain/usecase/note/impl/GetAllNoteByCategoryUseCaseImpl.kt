package uz.gita.notes_app.domain.usecase.note.impl

import uz.gita.notes_app.domain.usecase.note.GetAllNoteByCategoryUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class GetAllNoteByCategoryUseCaseImpl : GetAllNoteByCategoryUseCase {

    private val repository = NoteRepositoryImpl.getInstance()

    override fun getAllNotesByCategory(category: Int) = repository.getAllNoteByCategory(category)
}
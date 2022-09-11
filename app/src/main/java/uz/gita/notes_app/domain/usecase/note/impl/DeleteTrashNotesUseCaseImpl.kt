package uz.gita.notes_app.domain.usecase.note.impl

import uz.gita.notes_app.domain.usecase.note.DeleteTrashNotesUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class DeleteTrashNotesUseCaseImpl : DeleteTrashNotesUseCase {

    private val repository = NoteRepositoryImpl.getInstance()

    override suspend fun deleteAllTrashNotes() = repository.deleteAllTrashNotes()

}
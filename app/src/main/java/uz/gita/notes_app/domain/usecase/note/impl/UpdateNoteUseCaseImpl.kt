package uz.gita.notes_app.domain.usecase.note.impl

import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.UpdateNoteUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class UpdateNoteUseCaseImpl : UpdateNoteUseCase {

    private val repository = NoteRepositoryImpl.getInstance()

    override suspend fun updateNote(noteData: NoteData) = repository.updateNote(noteData)
}
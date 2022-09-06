package uz.gita.notes_app.domain.usecase.note.impl

import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.DeleteNoteUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class DeleteNoteUseCaseImpl : DeleteNoteUseCase {

    private val repository = NoteRepositoryImpl.getInstance()

    override suspend fun deleteNote(noteData: NoteData) = repository.deleteNote(noteData)
}
package uz.gita.notes_app.domain.usecase.note.impl

import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.AddNoteUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class AddNoteUseCaseImpl : AddNoteUseCase {
    private val repository = NoteRepositoryImpl.getInstance()
    override suspend fun addNote(noteData: NoteData) = repository.insertNote(noteData)
}
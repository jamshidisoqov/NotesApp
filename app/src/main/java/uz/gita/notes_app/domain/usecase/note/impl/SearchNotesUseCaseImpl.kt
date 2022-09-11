package uz.gita.notes_app.domain.usecase.note.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.SearchNotesUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class SearchNotesUseCaseImpl : SearchNotesUseCase {

    private val repository = NoteRepositoryImpl.getInstance()

    override fun searchNote(query: String): Flow<List<NoteData>> = repository.searchNotes(query)

}
package uz.gita.notes_app.domain.usecase.note.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.GetAllTrashUseCase
import uz.gita.notes_app.repository.impl.NoteRepositoryImpl

class GetAllTrashUseCaseImpl : GetAllTrashUseCase {
    private val repository = NoteRepositoryImpl.getInstance()
    override fun getAllTrash(): Flow<List<NoteData>> = repository.getAllTrash()
}
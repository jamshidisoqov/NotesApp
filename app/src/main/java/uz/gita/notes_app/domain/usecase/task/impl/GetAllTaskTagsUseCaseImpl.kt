package uz.gita.notes_app.domain.usecase.task.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.domain.usecase.task.GetAllTaskTagsUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class GetAllTaskTagsUseCaseImpl : GetAllTaskTagsUseCase {
    private val repository = TaskRepositoryImpl.getInstance()

    override fun getAllTags(): Flow<List<String>> = repository.getAllTags()
}
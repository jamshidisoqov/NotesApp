package uz.gita.notes_app.domain.usecase.task.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.SearchTaskUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class SearchTaskUseCaseImpl : SearchTaskUseCase {

    private val taskRepository = TaskRepositoryImpl.getInstance()

    override fun search(query: String): Flow<List<TaskData>> = taskRepository.search(query)
}
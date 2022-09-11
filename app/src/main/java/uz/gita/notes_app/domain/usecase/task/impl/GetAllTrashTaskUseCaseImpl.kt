package uz.gita.notes_app.domain.usecase.task.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.GetAllTrashTaskUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class GetAllTrashTaskUseCaseImpl : GetAllTrashTaskUseCase {

    private val repository = TaskRepositoryImpl.getInstance()
    override fun getAllTrashTasks(): Flow<List<TaskData>> = repository.getAllTrashes()
}
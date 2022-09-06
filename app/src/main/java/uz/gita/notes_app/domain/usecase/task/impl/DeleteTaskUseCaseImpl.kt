package uz.gita.notes_app.domain.usecase.task.impl

import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.DeleteTaskUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class DeleteTaskUseCaseImpl : DeleteTaskUseCase {
    private val repository = TaskRepositoryImpl.getInstance()
    override suspend fun deleteTask(taskData: TaskData) = repository.deleteTask(taskData)
}
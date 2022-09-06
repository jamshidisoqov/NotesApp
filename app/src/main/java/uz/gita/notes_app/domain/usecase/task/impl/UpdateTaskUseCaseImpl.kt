package uz.gita.notes_app.domain.usecase.task.impl

import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.UpdateTaskUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class UpdateTaskUseCaseImpl : UpdateTaskUseCase {
    private val repository = TaskRepositoryImpl.getInstance()

    override suspend fun updateTask(taskData: TaskData)  =
        repository.updateTask(taskData)


}
package uz.gita.notes_app.domain.usecase.task.impl

import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.AddTaskUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class AddTaskUseCaseImpl : AddTaskUseCase {

    private val repository = TaskRepositoryImpl.getInstance()

    override suspend fun addTask(taskData: TaskData)  = repository.insertTask(taskData)
}
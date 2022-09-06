package uz.gita.notes_app.domain.usecase.task.impl

import uz.gita.notes_app.data.models.TaskCategoryData
import uz.gita.notes_app.domain.usecase.task.AddTaskCategoryUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class AddTaskCategoryUseCaseImpl : AddTaskCategoryUseCase {

    private val repository = TaskRepositoryImpl.getInstance()

    override suspend fun addTaskCategory(taskCategoryData: TaskCategoryData) =
        repository.insertTaskCategory(taskCategoryData)
}
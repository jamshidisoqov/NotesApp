package uz.gita.notes_app.domain.usecase.task.impl

import uz.gita.notes_app.data.models.TaskCategoryData
import uz.gita.notes_app.domain.usecase.task.DeleteTaskCategoryUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class DeleteTaskCategoryUseCaseImpl : DeleteTaskCategoryUseCase {
    private val repository = TaskRepositoryImpl.getInstance()
    override suspend fun deleteTaskCategory(taskCategoryData: TaskCategoryData) =
        repository.deleteTaskCategory(taskCategoryData)
}
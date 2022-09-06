package uz.gita.notes_app.domain.usecase.task.impl

import uz.gita.notes_app.domain.usecase.task.GetAllTaskCategoryUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class GetAllTaskCategoryUseCaseImpl : GetAllTaskCategoryUseCase {

    private val repository = TaskRepositoryImpl.getInstance()

    override fun getAllTaskCategory() = repository.getAllTaskCategory()
}
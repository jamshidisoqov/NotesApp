package uz.gita.notes_app.domain.usecase.task.impl

import uz.gita.notes_app.domain.usecase.task.GetAllTasksByCategoryUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class GetAllTasksByCategoryUseCaseImpl : GetAllTasksByCategoryUseCase {

    private val repository = TaskRepositoryImpl.getInstance()

    override fun getAllTaskByCategory(category: Int) = repository.getAllTasksByCategory(category)

}
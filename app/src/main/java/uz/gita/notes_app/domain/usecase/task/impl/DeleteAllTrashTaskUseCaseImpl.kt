package uz.gita.notes_app.domain.usecase.task.impl

import uz.gita.notes_app.domain.usecase.task.DeleteAllTrashTaskUseCase
import uz.gita.notes_app.repository.impl.TaskRepositoryImpl

class DeleteAllTrashTaskUseCaseImpl : DeleteAllTrashTaskUseCase {
    private val repository = TaskRepositoryImpl.getInstance()
    override suspend fun clearTrashTasks() = repository.clearTrashes()
}
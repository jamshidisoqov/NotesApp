package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.DeleteTaskUseCase
import uz.gita.notes_app.domain.usecase.task.UpdateTaskUseCase
import uz.gita.notes_app.domain.usecase.task.impl.DeleteTaskUseCaseImpl
import uz.gita.notes_app.domain.usecase.task.impl.UpdateTaskUseCaseImpl
import uz.gita.notes_app.presenter.TrashTaskDetailsViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class TrashTaskDetailsViewModelImpl : TrashTaskDetailsViewModel, ViewModel() {

    private val updateTaskUseCase: UpdateTaskUseCase = UpdateTaskUseCaseImpl()

    private val deleteTaskUseCase: DeleteTaskUseCase = DeleteTaskUseCaseImpl()

    override val restoreTaskLiveData = eventLiveData<TaskData>()

    override val deleteTaskLiveData = eventLiveData<TaskData>()

    override val backLiveData = emptyLiveData()

    override fun restoreClick(taskData: TaskData) {
        restoreTaskLiveData.value = taskData
    }

    override fun deleteClick(taskData: TaskData) {
        deleteTaskLiveData.value = taskData
    }

    override fun backClick() {
        backLiveData.value = Unit
    }

    override fun restore(taskData: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase.updateTask(taskData.copy(status = 1))
        }
    }

    override fun delete(taskData: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase.deleteTask(taskData)
        }
    }
}
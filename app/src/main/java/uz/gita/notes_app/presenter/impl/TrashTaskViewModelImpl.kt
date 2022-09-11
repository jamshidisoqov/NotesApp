package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.DeleteAllTrashTaskUseCase
import uz.gita.notes_app.domain.usecase.task.DeleteTaskUseCase
import uz.gita.notes_app.domain.usecase.task.impl.DeleteAllTrashTaskUseCaseImpl
import uz.gita.notes_app.domain.usecase.task.impl.DeleteTaskUseCaseImpl
import uz.gita.notes_app.presenter.TrashTaskViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class TrashTaskViewModelImpl : TrashTaskViewModel, ViewModel() {

    private val deleteTaskUseCase: DeleteTaskUseCase = DeleteTaskUseCaseImpl()

    private val deleteAllTrashTaskUseCase: DeleteAllTrashTaskUseCase =
        DeleteAllTrashTaskUseCaseImpl()

    override val backLiveData = emptyLiveData()

    override val allTrashTasksLiveData = eventLiveData<List<TaskData>>()

    override val deleteTaskLiveData = eventLiveData<TaskData>()

    override val openDetailsTaskLiveData = eventLiveData<TaskData>()

    override val deleteAllTaskLiveData = emptyLiveData()

    override fun backClicked() {
        backLiveData.value = Unit
    }

    override fun taskItemClick(taskData: TaskData) {
        openDetailsTaskLiveData.value = taskData
    }

    override fun deleteAllTaskClick() {
        deleteAllTaskLiveData.value = Unit
    }

    override fun taskDeleteClick(taskData: TaskData) {
        deleteTaskLiveData.value = taskData
    }

    override fun deleteTask(taskData: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase.deleteTask(taskData)
        }
    }

    override fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllTrashTaskUseCase.clearTrashTasks()
        }
    }
}
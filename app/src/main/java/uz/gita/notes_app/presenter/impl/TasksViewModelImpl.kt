package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.notes_app.data.models.TaskCategoryData
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.AddTaskCategoryUseCase
import uz.gita.notes_app.domain.usecase.task.DeleteTaskUseCase
import uz.gita.notes_app.domain.usecase.task.GetAllTaskCategoryUseCase
import uz.gita.notes_app.domain.usecase.task.UpdateTaskUseCase
import uz.gita.notes_app.domain.usecase.task.impl.AddTaskCategoryUseCaseImpl
import uz.gita.notes_app.domain.usecase.task.impl.DeleteTaskUseCaseImpl
import uz.gita.notes_app.domain.usecase.task.impl.GetAllTaskCategoryUseCaseImpl
import uz.gita.notes_app.domain.usecase.task.impl.UpdateTaskUseCaseImpl
import uz.gita.notes_app.presenter.TasksViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class TasksViewModelImpl : TasksViewModel, ViewModel() {

    private val updateTaskUseCase: UpdateTaskUseCase = UpdateTaskUseCaseImpl()

    private val addTaskUseCase: AddTaskCategoryUseCase = AddTaskCategoryUseCaseImpl()

    private val deleteTaskUseCase: DeleteTaskUseCase = DeleteTaskUseCaseImpl()

    private val categoryTaskUseCase: GetAllTaskCategoryUseCase = GetAllTaskCategoryUseCaseImpl()

    override val backLiveData = emptyLiveData()

    override val searchLiveData = emptyLiveData()

    override val supportLiveData = emptyLiveData()

    override val addTaskLiveData = emptyLiveData()

    override val editTaskLiveData = eventLiveData<TaskData>()

    override val deleteTaskLiveData = eventLiveData<TaskData>()

    override val taskLiveData = eventLiveData<List<TaskData>>()

    override val addCategoryLiveData = emptyLiveData()

    override val categoryListLiveData = eventLiveData<List<TaskCategoryData>>()

    override fun addCategoryClick() {
        addCategoryLiveData.value = Unit
    }

    override fun editItemClick(taskData: TaskData) {
        editTaskLiveData.value = taskData
    }

    override fun editItem(taskData: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase.updateTask(taskData)
        }
    }

    override fun deleteItemClick(taskData: TaskData) {
        deleteTaskLiveData.value = taskData
    }

    override fun addCategory(taskCategoryData: TaskCategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase.addTaskCategory(taskCategoryData)
        }
    }

    override fun deleteTask(taskData: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase.deleteTask(taskData)
        }
    }

    override fun categoryClick(categoryData: TaskCategoryData) {
        viewModelScope.launch {
            categoryTaskUseCase.getAllTaskCategory().onEach {
                categoryListLiveData.value = it
            }.launchIn(this)
        }
    }

    override fun backClick() {
        backLiveData.value = Unit
    }

    override fun addTask() {
        addTaskLiveData.value = Unit
    }
}
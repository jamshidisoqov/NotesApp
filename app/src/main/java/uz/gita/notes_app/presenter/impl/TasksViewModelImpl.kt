package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.notes_app.data.models.TaskCategoryData
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.*
import uz.gita.notes_app.domain.usecase.task.impl.*
import uz.gita.notes_app.presenter.TasksViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class TasksViewModelImpl : TasksViewModel, ViewModel() {

    private val updateTaskUseCase: UpdateTaskUseCase = UpdateTaskUseCaseImpl()

    private val addTaskUseCase: AddTaskCategoryUseCase = AddTaskCategoryUseCaseImpl()

    private val deleteTaskUseCase: DeleteTaskUseCase = DeleteTaskUseCaseImpl()

    private val getAllTasksByCategoryUseCase: GetAllTasksByCategoryUseCase =
        GetAllTasksByCategoryUseCaseImpl()

    private val categoryTaskUseCase: GetAllTaskCategoryUseCase = GetAllTaskCategoryUseCaseImpl()

    override val searchLiveData = emptyLiveData()

    override val supportLiveData = emptyLiveData()

    override val addTaskLiveData = emptyLiveData()

    override val editTaskLiveData = eventLiveData<TaskData>()

    override val deleteTaskLiveData = eventLiveData<TaskData>()

    override val taskLiveData = eventLiveData<List<TaskData>>()

    override val addCategoryLiveData = emptyLiveData()

    override val categoryListLiveData = eventLiveData<List<TaskCategoryData>>()

    init {
        categoryTaskUseCase.getAllTaskCategory()
            .onEach {
                categoryListLiveData.value = it
                categoryClick(it[0].id)
            }
            .launchIn(viewModelScope)
    }

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

    override fun categoryClick(category: Int) {
        viewModelScope.launch {
            getAllTasksByCategoryUseCase.getAllTaskByCategory(category)
                .onEach {
                    taskLiveData.value = it
                }.launchIn(this)
        }
    }

    override fun addTask() {
        addTaskLiveData.value = Unit
    }

    override fun supportClick() {
        supportLiveData.value = Unit
    }
}
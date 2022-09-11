package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val getAllTasksByCategoryUseCase: GetAllTasksByCategoryUseCase =
        GetAllTasksByCategoryUseCaseImpl()

    private val deleteTaskCategory: DeleteTaskCategoryUseCase = DeleteTaskCategoryUseCaseImpl()

    private val categoryTaskUseCase: GetAllTaskCategoryUseCase = GetAllTaskCategoryUseCaseImpl()

    override val searchLiveData = emptyLiveData()

    override val supportLiveData = emptyLiveData()

    override val addTaskLiveData = emptyLiveData()

    override val editTaskLiveData = eventLiveData<TaskData>()

    override val deleteTaskLiveData = eventLiveData<TaskData>()

    override val taskLiveData = eventLiveData<List<TaskData>>()

    override val addCategoryLiveData = emptyLiveData()

    override val categoryListLiveData = eventLiveData<List<TaskCategoryData>>()

    override val basketLiveData = emptyLiveData()

    override val deleteCategoryLiveData = eventLiveData<TaskCategoryData>()

    init {
        viewModelScope.launch {
            categoryTaskUseCase.getAllTaskCategory()
                .onEach {
                    categoryListLiveData.value = it
                    if (it.isNotEmpty())
                    categoryClick(it[0].id)
                }.launchIn(this)
        }
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
            updateTaskUseCase.updateTask(taskData.copy(status = 2))
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

    override fun basketClicked() {
        basketLiveData.value = Unit
    }

    override fun supportClick() {
        supportLiveData.value = Unit
    }

    override fun deleteCategoryClick(taskCategoryData: TaskCategoryData) {
        deleteCategoryLiveData.value = taskCategoryData
    }

    override fun deleteCategory(taskCategoryData: TaskCategoryData) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteTaskCategory.deleteTaskCategory(taskCategoryData)
            }
        }
    }

    override fun searchClicked() {
        searchLiveData.value = Unit
    }
}
package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.*
import uz.gita.notes_app.domain.usecase.task.impl.*
import uz.gita.notes_app.presenter.SearchTasksViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class SearchTasksViewModelImpl : SearchTasksViewModel, ViewModel() {

    private val searchUseCase: SearchTaskUseCase = SearchTaskUseCaseImpl()

    private val updateTaskUseCase:UpdateTaskUseCase  = UpdateTaskUseCaseImpl()

    private val getALlTasks: GetAllTasksByCategoryUseCase = GetAllTasksByCategoryUseCaseImpl()

    private val getAllTags: GetAllTaskTagsUseCase = GetAllTaskTagsUseCaseImpl()

    override val taskLiveData = eventLiveData<List<TaskData>>()

    private var job: Job? = null

    override val editLiveData = eventLiveData<TaskData>()

    override val deleteLiveData = eventLiveData<TaskData>()

    override val backLiveData = emptyLiveData()

    override val allTagsLiveData = eventLiveData<List<String>>()

    init {
        viewModelScope.launch {
            getAllTags.getAllTags().collectLatest {
                allTagsLiveData.value = it
            }
        }
    }

    override fun deleteClick(taskData: TaskData) {
        deleteLiveData.value = taskData
    }

    override fun delete(taskData: TaskData) {
        viewModelScope.launch {
            updateTaskUseCase.updateTask(taskData.copy(status = 2))
        }
    }

    override fun editClick(taskData: TaskData) {
        editLiveData.value = taskData
    }

    override fun editTask(taskData: TaskData) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                updateTaskUseCase.updateTask(taskData)
            }
        }
    }

    override fun search(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(500)
            searchUseCase.search(query).collectLatest {
                taskLiveData.value = it
            }
        }
    }

    override fun back() {
        backLiveData.value = Unit
    }
}
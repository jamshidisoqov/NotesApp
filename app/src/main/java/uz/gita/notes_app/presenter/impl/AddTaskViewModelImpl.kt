package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.AddTaskUseCase
import uz.gita.notes_app.domain.usecase.task.impl.AddTaskUseCaseImpl
import uz.gita.notes_app.presenter.AddTaskViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class AddTaskViewModelImpl : AddTaskViewModel, ViewModel() {

    private val addTaskUseCase: AddTaskUseCase = AddTaskUseCaseImpl()

    override val backLiveData = emptyLiveData()

    override val saveLiveData = emptyLiveData()

    override val changeTypeLiveData = eventLiveData<Int>()

    override fun changeType(type: Int) {
        changeTypeLiveData.value = type
    }

    override fun backClick() {
        backLiveData.value = Unit
    }

    override fun saveData(taskData: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase.addTask(taskData)
            withContext(this.coroutineContext) {
                saveLiveData.value = Unit
            }
        }
    }
}
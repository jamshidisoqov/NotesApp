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

    override val changesLiveData = eventLiveData<Pair<Int, Boolean>>()

    override val messageLiveData = eventLiveData<String>()

    private val types = ArrayList<Int>()

    override fun changeType(type: Int) {
        val ty = type - 1
        if (types.contains(ty)) {
            types.remove(ty)
            changesLiveData.value = Pair(ty, false)
        } else {
            types.add(ty)
            changesLiveData.value = Pair(ty, true)
        }
        changeTypeLiveData.value = type
    }

    override fun backClick() {
        backLiveData.value = Unit
    }

    override fun saveData(taskData: TaskData) {

        if (taskData.title == "") {
            messageLiveData.value = "Title is empty, please enter a title"
            return
        }
        if (taskData.description == "") {
            messageLiveData.value = "Description is empty, please enter a title"
            return
        }
        messageLiveData.value = "Successfully added"
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase.addTask(taskData)
            withContext(Dispatchers.Main) {
                saveLiveData.value = Unit
            }
        }
    }
}
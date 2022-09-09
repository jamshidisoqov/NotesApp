package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.domain.usecase.task.UpdateTaskUseCase
import uz.gita.notes_app.domain.usecase.task.impl.UpdateTaskUseCaseImpl
import uz.gita.notes_app.presenter.UpdateTaskViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class UpdateTaskViewModelImpl : UpdateTaskViewModel, ViewModel() {

    private val updateTaskUseCase: UpdateTaskUseCase = UpdateTaskUseCaseImpl()

    override val backLiveData = emptyLiveData()

    override val updateLiveData = emptyLiveData()

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

    override fun updateTask(taskData: TaskData) {

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
            updateTaskUseCase.updateTask(taskData)
            updateLiveData.postValue(Unit)
        }
    }
}
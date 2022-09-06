package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.notes_app.presenter.MainViewModel

class MainViewModelImpl : MainViewModel, ViewModel() {

    override val openNoteLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openTaskLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openOptionDialogLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun openNote() {
        openNoteLiveData.value = Unit
    }

    override fun openTask() {
        openTaskLiveData.value = Unit
    }

    override fun openOption() {
        openOptionDialogLiveData.value = Unit
    }
}
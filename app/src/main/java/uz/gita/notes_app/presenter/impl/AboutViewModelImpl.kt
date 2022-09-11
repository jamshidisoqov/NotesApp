package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import uz.gita.notes_app.presenter.AboutViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData

class AboutViewModelImpl : AboutViewModel,ViewModel() {
    override val backLiveData = emptyLiveData()

    override fun back() {
        backLiveData.value = Unit
    }
}
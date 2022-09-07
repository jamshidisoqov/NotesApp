package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.task_app.domain.presenter.main.BottomMenuViewModel

class BottomMenuViewModelImpl : BottomMenuViewModel, ViewModel() {
    override val supportUsLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val shareAppLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val aboutUsLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun supportClick() {
        supportUsLiveData.postValue(Unit)
    }

    override fun shareClick() {
        shareAppLiveData.postValue(Unit)
    }

    override fun aboutClick() {
        aboutUsLiveData.postValue(Unit)
    }
}
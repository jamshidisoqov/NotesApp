package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.UpdateNoteUseCase
import uz.gita.notes_app.domain.usecase.note.impl.UpdateNoteUseCaseImpl
import uz.gita.notes_app.presenter.UpdateNoteViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class UpdateNoteViewModelImpl : UpdateNoteViewModel, ViewModel() {

    val updateNoteUseCase: UpdateNoteUseCase = UpdateNoteUseCaseImpl()

    override val backLiveData = emptyLiveData()

    override val updateLiveData = emptyLiveData()

    override val changeTypeLiveData = eventLiveData<Int>()

    override fun changeType(type: Int) {
        changeTypeLiveData.value = type
    }

    override fun backClick() {
        backLiveData.value = Unit
    }

    override fun updateNote(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.updateNote(noteData)
            withContext(this.coroutineContext) {
                updateLiveData.value = Unit
            }
        }
    }
}
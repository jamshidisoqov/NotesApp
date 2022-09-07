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

    private val updateNoteUseCase: UpdateNoteUseCase = UpdateNoteUseCaseImpl()

    override val backLiveData = emptyLiveData()

    override val updateLiveData = emptyLiveData()

    override val changeTypeLiveData = eventLiveData<Int>()

    override val changesLiveData = eventLiveData<Pair<Int, Boolean>>()

    private val types = ArrayList<Int>()

    override val messageLiveData = eventLiveData<String>()

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

    override fun updateNote(noteData: NoteData) {

        if (noteData.title == "") {
            messageLiveData.value = "Title is empty, please enter a title"
            return
        }
        if (noteData.description == "") {
            messageLiveData.value = "Description is empty, please enter a title"
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.updateNote(noteData)
            withContext(Dispatchers.Main) {
                updateLiveData.value = Unit
            }
        }
        messageLiveData.value = "Successfully updated"
    }
}
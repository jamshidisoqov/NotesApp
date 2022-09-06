package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.AddNoteUseCase
import uz.gita.notes_app.domain.usecase.note.impl.AddNoteUseCaseImpl
import uz.gita.notes_app.presenter.AddNoteViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class AddNoteViewModelImpl : AddNoteViewModel, ViewModel() {

    private val addNoteUseCase: AddNoteUseCase = AddNoteUseCaseImpl()

    override val backLiveData = emptyLiveData()

    override val saveLiveData = emptyLiveData()

    override val changeTypeLiveData = eventLiveData<Int>()

    override fun changeType(type: Int) {
        changeTypeLiveData.value = type
    }

    override fun backClick() {
        backLiveData.value = Unit
    }


    override fun saveData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.addNote(noteData)
            withContext(this.coroutineContext) {
                saveLiveData.value = Unit
            }
        }
    }
}
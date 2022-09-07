package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val types = ArrayList<Int>()

    override val changesLiveData = eventLiveData<Pair<Int, Boolean>>()

    override val messageLiveData:MutableLiveData<String> = MutableLiveData()

    override fun changeType(type: Int) {
        val ty = type-1
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


    override fun saveData(noteData: NoteData) {
        if (noteData.title==""){
            messageLiveData.value = "Title is empty, please enter a title"
            return
        }
        if (noteData.description=="") {
            messageLiveData.value = "Description is empty, please enter a title"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.addNote(noteData)
            withContext(Dispatchers.Main) {
                saveLiveData.value = Unit
            }
        }
        messageLiveData.value = "Successfully added"
    }
}
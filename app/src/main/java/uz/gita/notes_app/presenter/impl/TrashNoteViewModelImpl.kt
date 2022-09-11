package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.DeleteNoteUseCase
import uz.gita.notes_app.domain.usecase.note.DeleteTrashNotesUseCase
import uz.gita.notes_app.domain.usecase.note.GetAllTrashUseCase
import uz.gita.notes_app.domain.usecase.note.impl.DeleteNoteUseCaseImpl
import uz.gita.notes_app.domain.usecase.note.impl.DeleteTrashNotesUseCaseImpl
import uz.gita.notes_app.domain.usecase.note.impl.GetAllTrashUseCaseImpl
import uz.gita.notes_app.presenter.TrashNoteViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class TrashNoteViewModelImpl : TrashNoteViewModel, ViewModel() {

    private val trashesUseCase: GetAllTrashUseCase = GetAllTrashUseCaseImpl()

    private val deleteNoteUseCase: DeleteNoteUseCase = DeleteNoteUseCaseImpl()

    private val deleteAllNotesUseCase: DeleteTrashNotesUseCase = DeleteTrashNotesUseCaseImpl()

    override val allTrashNotesLiveData = eventLiveData<List<NoteData>>()

    override val backLiveData = emptyLiveData()

    override val deleteLiveData = eventLiveData<NoteData>()

    override val openDetailsTrashLiveData = eventLiveData<NoteData>()
    override val deleteAllNotesLiveData = emptyLiveData()

    init {
        viewModelScope.launch {
            trashesUseCase.getAllTrash().collectLatest {
                allTrashNotesLiveData.value = it
            }
        }
    }

    override fun backClicked() {
        backLiveData.value = Unit
    }

    override fun noteItemClick(noteData: NoteData) {
        openDetailsTrashLiveData.value = noteData
    }

    override fun deleteAllNotesClick() {
        deleteAllNotesLiveData.value = Unit
    }

    override fun noteDeleteClick(noteData: NoteData) {
        deleteLiveData.value = noteData
    }

    override fun deleteNote(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.deleteNote(noteData)
        }
    }

    override fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllNotesUseCase.deleteAllTrashNotes()
        }
    }
}
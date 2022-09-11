package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.DeleteNoteUseCase
import uz.gita.notes_app.domain.usecase.note.UpdateNoteUseCase
import uz.gita.notes_app.domain.usecase.note.impl.DeleteNoteUseCaseImpl
import uz.gita.notes_app.domain.usecase.note.impl.UpdateNoteUseCaseImpl
import uz.gita.notes_app.presenter.TrashNoteDetailsViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class TrashNoteDetailsViewModelImpl : TrashNoteDetailsViewModel, ViewModel() {

    private val updateNoteUseCase: UpdateNoteUseCase = UpdateNoteUseCaseImpl()

    private val deleteNoteUseCase: DeleteNoteUseCase = DeleteNoteUseCaseImpl()

    override val restoreNoteLiveData = eventLiveData<NoteData>()

    override val deleteNoteLiveData = eventLiveData<NoteData>()

    override val backLiveData = emptyLiveData()

    override fun restoreClick(noteData: NoteData) {
        restoreNoteLiveData.value = noteData
    }

    override fun deleteClick(noteData: NoteData) {
        deleteNoteLiveData.value = noteData
    }

    override fun backClick() {
        backLiveData.value = Unit
    }

    override fun restore(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.updateNote(noteData.copy(status = 1))
        }
    }

    override fun delete(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.deleteNote(noteData)
        }
    }
}
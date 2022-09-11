package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.*
import uz.gita.notes_app.domain.usecase.note.impl.*
import uz.gita.notes_app.presenter.SearchNotesViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

@FlowPreview
class SearchNotesViewModelImpl : SearchNotesViewModel, ViewModel() {

    private val deleteUseCase: DeleteNoteUseCase = DeleteNoteUseCaseImpl()

    private val updateNoteUseCase :UpdateNoteUseCase = UpdateNoteUseCaseImpl()

    private val getAllTags: GetAllNoteTagsUseCase = GetAllNoteTagsUseCaseImpl()

    private val searchNotes: SearchNotesUseCase = SearchNotesUseCaseImpl()

    override val notesLiveData = eventLiveData<List<NoteData>>()

    private var job: Job? = null

    override val editLiveData = eventLiveData<NoteData>()

    override val deleteLiveData = eventLiveData<NoteData>()

    override val backLiveData = emptyLiveData()

    override val allTagsLiveData = eventLiveData<List<String>>()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getAllTags.getAllNoteTags().collectLatest {
                    allTagsLiveData.postValue(it)
                }
            }
        }
    }


    override fun deleteClick(noteData: NoteData) {
        deleteLiveData.value = noteData
    }

    override fun delete(noteData: NoteData) {
        viewModelScope.launch {
            updateNoteUseCase.updateNote(noteData.copy(status = 2))
        }
    }

    override fun editClick(noteData: NoteData) {
        editLiveData.value = noteData
    }

    override fun search(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(500)
            searchNotes.searchNote(query)
                .collectLatest {
                    notesLiveData.value = it
                }
        }
    }

    override fun back() {
        backLiveData.value = Unit
    }
}
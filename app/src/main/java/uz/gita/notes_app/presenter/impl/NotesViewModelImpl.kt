package uz.gita.notes_app.presenter.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.domain.usecase.note.*
import uz.gita.notes_app.domain.usecase.note.impl.*
import uz.gita.notes_app.presenter.NotesViewModel
import uz.gita.notes_app.utils.extensions.emptyLiveData
import uz.gita.notes_app.utils.extensions.eventLiveData

class NotesViewModelImpl : NotesViewModel, ViewModel() {

    private val getAllNoteByCategoryUseCase: GetAllNoteByCategoryUseCase =
        GetAllNoteByCategoryUseCaseImpl()

    private val deleteCategoryUseCase: DeleteNoteCategoryUseCase = DeleteNoteCategoryUseCaseImpl()

    private val getAllCategory: GetAllNoteCategoryUseCase = GetAllNoteCategoryUseCaseImpl()

    private val updateNoteUseCase: UpdateNoteUseCase = UpdateNoteUseCaseImpl()

    private val addNoteCategoryUseCase: AddNoteCategoryUseCase = AddNoteCategoryUseCaseImpl()

    override val searchLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val supportLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val addNoteLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val editNoteLiveData: MutableLiveData<NoteData> = MutableLiveData()

    override val deleteNoteLiveData: MutableLiveData<NoteData> = MutableLiveData()

    override val notesLiveData: MutableLiveData<List<NoteData>> = MutableLiveData()

    override val addCategoryLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openTrashLiveData = emptyLiveData()

    override val deleteCategoryLiveData = eventLiveData<NoteCategoryData>()

    override val categoryListLiveData: MutableLiveData<List<NoteCategoryData>> = MutableLiveData()


    init {
        viewModelScope.launch {
            getAllCategory.getAllNoteCategory().onEach {
                categoryListLiveData.value = it
                if (it.isNotEmpty())
                categoryClick(it[0].id)
            }.launchIn(this)
        }
    }

    override fun addCategoryClick() {
        addCategoryLiveData.value = Unit
    }

    override fun editItemClick(noteData: NoteData) {
        editNoteLiveData.value = noteData
    }

    override fun deleteItemClick(noteData: NoteData) {
        deleteNoteLiveData.value = noteData
    }

    override fun categoryDeleteClick(noteCategoryData: NoteCategoryData) {
        deleteCategoryLiveData.value = noteCategoryData
    }

    override fun addCategory(categoryData: NoteCategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteCategoryUseCase.addNoteCategory(categoryData)
        }
    }

    override fun deleteNote(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.updateNote(noteData.copy(status = 2))
        }
    }

    override fun deleteCategory(noteCategoryData: NoteCategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCategoryUseCase.deleteNoteCategory(noteCategoryData)
        }
    }

    override fun categoryClick(category: Int) {
        viewModelScope.launch {
            getAllNoteByCategoryUseCase.getAllNotesByCategory(category)
                .onEach {
                    notesLiveData.value = it
                }.launchIn(this)
        }
    }

    override fun addNote() {
        addNoteLiveData.value = Unit
    }

    override fun supportClick() {
        supportLiveData.value = Unit
    }

    override fun searchClick() {
        searchLiveData.value = Unit
    }

    override fun basketClicked() {
        openTrashLiveData.value = Unit
    }
}
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
import uz.gita.notes_app.domain.usecase.note.AddNoteCategoryUseCase
import uz.gita.notes_app.domain.usecase.note.DeleteNoteUseCase
import uz.gita.notes_app.domain.usecase.note.GetAllNoteByCategoryUseCase
import uz.gita.notes_app.domain.usecase.note.GetAllNoteCategoryUseCase
import uz.gita.notes_app.domain.usecase.note.impl.AddNoteCategoryUseCaseImpl
import uz.gita.notes_app.domain.usecase.note.impl.DeleteNoteUseCaseImpl
import uz.gita.notes_app.domain.usecase.note.impl.GetAllNoteByCategoryUseCaseImpl
import uz.gita.notes_app.domain.usecase.note.impl.GetAllNoteCategoryUseCaseImpl
import uz.gita.notes_app.presenter.NotesViewModel

class NotesViewModelImpl : NotesViewModel, ViewModel() {

    private val getAllNoteByCategoryUseCase: GetAllNoteByCategoryUseCase =
        GetAllNoteByCategoryUseCaseImpl()

    private val getAllCategory: GetAllNoteCategoryUseCase = GetAllNoteCategoryUseCaseImpl()

    private val deleteNoteUseCase: DeleteNoteUseCase = DeleteNoteUseCaseImpl()

    private val addNoteCategoryUseCase: AddNoteCategoryUseCase = AddNoteCategoryUseCaseImpl()

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val searchLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val supportLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val addNoteLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val editNoteLiveData: MutableLiveData<NoteData> = MutableLiveData()

    override val deleteNoteLiveData: MutableLiveData<NoteData> = MutableLiveData()

    override val notesLiveData: MutableLiveData<List<NoteData>> = MutableLiveData()

    override val addCategoryLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val categoryListLiveData: MutableLiveData<List<NoteCategoryData>> = MutableLiveData()

    init {
        viewModelScope.launch {
            getAllCategory.getAllNoteCategory().onEach {
                categoryListLiveData.value = it
            }.launchIn(this)

            getAllNoteByCategoryUseCase.getAllNotesByCategory().onEach {
                notesLiveData.value = it
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

    override fun addCategory(categoryData: NoteCategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteCategoryUseCase.addNoteCategory(categoryData)
        }
    }

    override fun deleteNote(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.deleteNote(noteData)
        }
    }

    override fun categoryClick(categoryData: NoteCategoryData) {
        viewModelScope.launch {
            getAllNoteByCategoryUseCase.getAllNotesByCategory(categoryData.id)
                .onEach {
                    notesLiveData.value = it
                }.launchIn(this)
        }
    }

    override fun backClick() {
        backLiveData.value = Unit
    }

    override fun addNote() {
        addNoteLiveData.value = Unit
    }
}
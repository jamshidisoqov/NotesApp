package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/6/2022
interface NotesViewModel {

    val searchLiveData: LiveData<Unit>

    val supportLiveData: LiveData<Unit>

    val addNoteLiveData: LiveData<Unit>

    val editNoteLiveData: LiveData<NoteData>

    val deleteNoteLiveData: LiveData<NoteData>

    val notesLiveData: LiveData<List<NoteData>>

    val addCategoryLiveData: LiveData<Unit>

    val openTrashLiveData: LiveData<Unit>

    val deleteCategoryLiveData: LiveData<NoteCategoryData>

    val categoryListLiveData: LiveData<List<NoteCategoryData>>

    fun addCategoryClick()

    fun editItemClick(noteData: NoteData)

    fun deleteItemClick(noteData: NoteData)

    fun categoryDeleteClick(noteCategoryData: NoteCategoryData)

    fun addCategory(categoryData: NoteCategoryData)

    fun deleteNote(noteData: NoteData)

    fun deleteCategory(noteCategoryData: NoteCategoryData)

    fun categoryClick(category: Int)

    fun addNote()

    fun supportClick()

    fun searchClick()

    fun basketClicked()


}
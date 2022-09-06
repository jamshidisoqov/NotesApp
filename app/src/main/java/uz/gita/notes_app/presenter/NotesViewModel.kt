package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/6/2022
interface NotesViewModel {

    val backLiveData: LiveData<Unit>

    val searchLiveData: LiveData<Unit>

    val supportLiveData: LiveData<Unit>

    val addNoteLiveData: LiveData<Unit>

    val editNoteLiveData: LiveData<NoteData>

    val deleteNoteLiveData: LiveData<NoteData>

    val notesLiveData: LiveData<List<NoteData>>

    val addCategoryLiveData: LiveData<Unit>

    val categoryListLiveData: LiveData<List<NoteCategoryData>>

    fun addCategoryClick()

    fun editItemClick(noteData: NoteData)

    fun deleteItemClick(noteData: NoteData)

    fun addCategory(categoryData: NoteCategoryData)

    fun deleteNote(noteData: NoteData)

    fun categoryClick(categoryData: NoteCategoryData)

    fun backClick()

    fun addNote()

}
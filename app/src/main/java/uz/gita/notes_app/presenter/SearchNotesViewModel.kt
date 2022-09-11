package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/9/2022
interface SearchNotesViewModel {

    val notesLiveData: LiveData<List<NoteData>>

    val editLiveData: LiveData<NoteData>

    val deleteLiveData: LiveData<NoteData>

    val backLiveData: LiveData<Unit>

    val allTagsLiveData: LiveData<List<String>>

    fun deleteClick(noteData: NoteData)

    fun delete(noteData: NoteData)

    fun editClick(noteData: NoteData)

    fun search(query: String)

    fun back()

}
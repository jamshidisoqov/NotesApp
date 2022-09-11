package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/10/2022
interface TrashNoteDetailsViewModel {

    val restoreNoteLiveData: LiveData<NoteData>

    val deleteNoteLiveData: LiveData<NoteData>

    val backLiveData: LiveData<Unit>

    fun restoreClick(noteData: NoteData)

    fun deleteClick(noteData: NoteData)

    fun backClick()

    fun restore(noteData: NoteData)

    fun delete(noteData: NoteData)

}
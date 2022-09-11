package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/10/2022
interface TrashNoteViewModel {

    val allTrashNotesLiveData: LiveData<List<NoteData>>

    val backLiveData: LiveData<Unit>

    val deleteLiveData: LiveData<NoteData>

    val openDetailsTrashLiveData: LiveData<NoteData>

    val deleteAllNotesLiveData: LiveData<Unit>

    fun backClicked()

    fun noteItemClick(noteData: NoteData)

    fun deleteAllNotesClick()

    fun noteDeleteClick(noteData: NoteData)

    fun deleteNote(noteData: NoteData)

    fun deleteAllNotes()

}
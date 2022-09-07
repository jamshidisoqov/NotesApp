package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/6/2022
interface UpdateNoteViewModel {

    val backLiveData: LiveData<Unit>

    val updateLiveData: LiveData<Unit>

    val changeTypeLiveData: LiveData<Int>

    val changesLiveData: LiveData<Pair<Int, Boolean>>

    val messageLiveData:LiveData<String>

    fun changeType(type: Int)

    fun backClick()

    fun updateNote(noteData: NoteData)

}
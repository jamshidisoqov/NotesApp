package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData

// Created by Jamshid Isoqov an 9/6/2022
interface AddNoteViewModel {

    val backLiveData: LiveData<Unit>

    val saveLiveData: LiveData<Unit>
}
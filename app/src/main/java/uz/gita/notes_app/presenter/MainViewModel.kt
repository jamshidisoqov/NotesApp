package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData

// Created by Jamshid Isoqov an 9/6/2022
interface MainViewModel {

    val openNoteLiveData: LiveData<Unit>

    val openTaskLiveData: LiveData<Unit>

    val openOptionDialogLiveData: LiveData<Unit>

    fun openNote()

    fun openTask()

    fun openOption()

}
package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData

// Created by Jamshid Isoqov an 9/11/2022
interface AboutViewModel {

    val backLiveData:LiveData<Unit>

    fun back()

}
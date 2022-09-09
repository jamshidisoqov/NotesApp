package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/6/2022
interface AddTaskViewModel {

    val backLiveData: LiveData<Unit>

    val saveLiveData: LiveData<Unit>

    val changeTypeLiveData: LiveData<Int>

    val changesLiveData: LiveData<Pair<Int, Boolean>>

    val messageLiveData:LiveData<String>

    fun changeType(type: Int)

    fun backClick()

    fun saveData(taskData: TaskData)
}
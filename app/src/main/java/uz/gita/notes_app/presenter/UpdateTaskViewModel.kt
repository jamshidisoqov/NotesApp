package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/6/2022
interface UpdateTaskViewModel {

    val backLiveData: LiveData<Unit>

    val updateLiveData: LiveData<Unit>

    val changeTypeLiveData: LiveData<Int>

    fun changeType(type: Int)

    fun backClick()

    fun updateTask(taskData: TaskData)

}
package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/10/2022
interface TrashTaskDetailsViewModel {

    val restoreTaskLiveData: LiveData<TaskData>

    val deleteTaskLiveData: LiveData<TaskData>

    val backLiveData: LiveData<Unit>

    fun restoreClick(taskData: TaskData)

    fun deleteClick(taskData: TaskData)

    fun backClick()

    fun restore(taskData: TaskData)

    fun delete(taskData: TaskData)


}
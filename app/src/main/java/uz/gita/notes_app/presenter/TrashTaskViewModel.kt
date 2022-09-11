package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/10/2022
interface TrashTaskViewModel {

    val backLiveData: LiveData<Unit>

    val allTrashTasksLiveData: LiveData<List<TaskData>>

    val deleteTaskLiveData: LiveData<TaskData>

    val openDetailsTaskLiveData: LiveData<TaskData>

    val deleteAllTaskLiveData: LiveData<Unit>

    fun backClicked()

    fun taskItemClick(taskData: TaskData)

    fun deleteAllTaskClick()

    fun taskDeleteClick(taskData: TaskData)

    fun deleteTask(taskData: TaskData)

    fun deleteAllTasks()

}
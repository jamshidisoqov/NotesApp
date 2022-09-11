package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/9/2022
interface SearchTasksViewModel {

    val taskLiveData: LiveData<List<TaskData>>

    val editLiveData: LiveData<TaskData>

    val deleteLiveData: LiveData<TaskData>

    val backLiveData: LiveData<Unit>

    val allTagsLiveData: LiveData<List<String>>

    fun deleteClick(taskData: TaskData)

    fun delete(taskData: TaskData)

    fun editClick(taskData: TaskData)

    fun editTask(taskData: TaskData)

    fun search(query: String)

    fun back()

}
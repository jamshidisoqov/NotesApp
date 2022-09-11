package uz.gita.notes_app.presenter

import androidx.lifecycle.LiveData
import uz.gita.notes_app.data.models.TaskCategoryData
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/6/2022

interface TasksViewModel {

    val searchLiveData: LiveData<Unit>

    val supportLiveData: LiveData<Unit>

    val addTaskLiveData: LiveData<Unit>

    val editTaskLiveData: LiveData<TaskData>

    val deleteTaskLiveData: LiveData<TaskData>

    val taskLiveData: LiveData<List<TaskData>>

    val addCategoryLiveData: LiveData<Unit>

    val categoryListLiveData: LiveData<List<TaskCategoryData>>

    val basketLiveData: LiveData<Unit>

    val deleteCategoryLiveData: LiveData<TaskCategoryData>

    fun addCategoryClick()

    fun editItemClick(taskData: TaskData)

    fun editItem(taskData: TaskData)

    fun deleteItemClick(taskData: TaskData)

    fun addCategory(taskCategoryData: TaskCategoryData)

    fun deleteTask(taskData: TaskData)

    fun categoryClick(category: Int)

    fun addTask()

    fun basketClicked()

    fun supportClick()

    fun deleteCategoryClick(taskCategoryData: TaskCategoryData)

    fun deleteCategory(taskCategoryData: TaskCategoryData)

    fun searchClicked()
}
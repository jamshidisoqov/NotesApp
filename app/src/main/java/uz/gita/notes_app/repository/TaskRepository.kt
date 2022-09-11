package uz.gita.notes_app.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.TaskCategoryData
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/6/2022
interface TaskRepository {

    suspend fun insertTask(taskData: TaskData)

    suspend fun updateTask(taskData: TaskData)

    suspend fun deleteTask(taskData: TaskData)

    suspend fun insertTaskCategory(taskCategoryData: TaskCategoryData)

    fun getAllTasksByCategory(category: Int): Flow<List<TaskData>>

    fun getAllTaskCategory(): Flow<List<TaskCategoryData>>

    fun search(query: String): Flow<List<TaskData>>

    fun getAllTrashes():Flow<List<TaskData>>

    suspend fun clearTrashes()

    suspend fun deleteTaskCategory(taskCategoryData: TaskCategoryData)

    fun getAllTags():Flow<List<String>>

}
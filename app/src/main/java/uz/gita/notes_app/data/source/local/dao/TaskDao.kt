package uz.gita.notes_app.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.source.local.entity.TaskCategoryEntity
import uz.gita.notes_app.data.source.local.entity.TaskEntity

// Created by Jamshid Isoqov an 9/6/2022
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskCategory(taskCategoryEntity: TaskCategoryEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskList: List<TaskEntity>)

    @Query("SELECT * FROM tasks WHERE category=:category")
    fun getAllTasksByCategory(category: Int = 1): Flow<List<TaskEntity>>

    @Query("SELECT*FROM task_category")
    fun getAllCategory(): Flow<List<TaskCategoryEntity>>

}
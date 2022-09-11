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

    @Delete
    suspend fun deleteTaskCategory(taskCategoryEntity: TaskCategoryEntity)

    @Query("SELECT * FROM tasks WHERE category=:category AND status = 1")
    fun getAllTasksByCategory(category: Int = 1): Flow<List<TaskEntity>>

    @Query("SELECT*FROM task_category")
    fun getAllCategory(): Flow<List<TaskCategoryEntity>>

    @Query("SELECT*FROM tasks WHERE title LIKE '%' || :query || '%' AND status = 1 OR tag LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<TaskEntity>>

    @Query("SELECT*FROM tasks WHERE status = 1")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT*FROM tasks WHERE status = 2")
    fun getAllTrashes(): Flow<List<TaskEntity>>

    @Query("DELETE FROM tasks WHERE status=2")
    suspend fun clearTrashes()

    @Query("SELECT tag FROM tasks WHERE status = 1 AND NOT(tag isNull or tag ='')")
    fun getAllTags(): Flow<List<String>>


}
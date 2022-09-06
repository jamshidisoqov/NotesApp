package uz.gita.notes_app.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.gita.notes_app.data.models.TaskCategoryData
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.data.source.local.AppDatabase
import uz.gita.notes_app.repository.TaskRepository

class TaskRepositoryImpl private constructor() : TaskRepository {

    private val dao = AppDatabase.getInstance().taskDao()

    override suspend fun insertTask(taskData: TaskData) = dao.insertTask(taskData.toTaskEntity())

    override suspend fun updateTask(taskData: TaskData) = dao.updateTask(taskData.toTaskEntity())

    override suspend fun deleteTask(taskData: TaskData) = dao.deleteTask(taskData.toTaskEntity())

    override suspend fun insertTaskCategory(taskCategoryData: TaskCategoryData) =
        dao.insertTaskCategory(taskCategoryData.toTaskCategoryEntity())

    override fun getAllTasksByCategory(category: Int): Flow<List<TaskData>> =
        dao.getAllTasksByCategory(category).map { taskList ->
            taskList.map { taskEntity ->
                taskEntity.toTaskData()
            }
        }.flowOn(Dispatchers.IO)

    override fun getAllTaskCategory(): Flow<List<TaskCategoryData>> =
        dao.getAllCategory().map { taskCategoryList ->
            taskCategoryList.map { taskCategoryEntity ->
                taskCategoryEntity.toTaskCategoryData()
            }
        }.flowOn(Dispatchers.IO)

    companion object {
        private lateinit var instance: TaskRepository

        fun getInstance(): TaskRepository {
            if (!Companion::instance.isInitialized) {
                instance = TaskRepositoryImpl()
            }
            return instance
        }
    }
}
package uz.gita.notes_app.domain.usecase.task

import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/6/2022
interface AddTaskUseCase {
    suspend fun addTask(taskData: TaskData)
}
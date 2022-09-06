package uz.gita.notes_app.domain.usecase.task

import uz.gita.notes_app.data.models.TaskCategoryData

// Created by Jamshid Isoqov an 9/6/2022
interface AddTaskCategoryUseCase {
    suspend fun addTaskCategory(taskCategoryData: TaskCategoryData)
}
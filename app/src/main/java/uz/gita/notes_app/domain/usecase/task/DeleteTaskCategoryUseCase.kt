package uz.gita.notes_app.domain.usecase.task

import uz.gita.notes_app.data.models.TaskCategoryData

// Created by Jamshid Isoqov an 9/11/2022
interface DeleteTaskCategoryUseCase {

    suspend fun deleteTaskCategory(taskCategoryData: TaskCategoryData)

}
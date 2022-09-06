package uz.gita.notes_app.domain.usecase.task

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.TaskCategoryData

// Created by Jamshid Isoqov an 9/6/2022
interface GetAllTaskCategoryUseCase {
    fun getAllTaskCategory(): Flow<List<TaskCategoryData>>
}
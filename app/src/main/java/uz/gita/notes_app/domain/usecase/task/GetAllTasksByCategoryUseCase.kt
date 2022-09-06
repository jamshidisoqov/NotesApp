package uz.gita.notes_app.domain.usecase.task

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/6/2022
interface GetAllTasksByCategoryUseCase {

    fun getAllTaskByCategory(category:Int):Flow<List<TaskData>>

}
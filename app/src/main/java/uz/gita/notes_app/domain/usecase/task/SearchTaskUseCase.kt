package uz.gita.notes_app.domain.usecase.task

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/9/2022
interface SearchTaskUseCase {

    fun search(query: String): Flow<List<TaskData>>

}
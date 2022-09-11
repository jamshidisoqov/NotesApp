package uz.gita.notes_app.domain.usecase.task

import kotlinx.coroutines.flow.Flow

// Created by Jamshid Isoqov an 9/11/2022
interface GetAllTaskTagsUseCase {

    fun getAllTags():Flow<List<String>>

}
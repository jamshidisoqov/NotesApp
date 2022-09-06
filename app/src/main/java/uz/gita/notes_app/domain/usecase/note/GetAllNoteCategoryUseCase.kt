package uz.gita.notes_app.domain.usecase.note

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteCategoryData

// Created by Jamshid Isoqov an 9/6/2022
interface GetAllNoteCategoryUseCase {
    fun getAllNoteCategory(): Flow<List<NoteCategoryData>>
}
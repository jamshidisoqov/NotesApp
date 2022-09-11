package uz.gita.notes_app.domain.usecase.note

import uz.gita.notes_app.data.models.NoteCategoryData

// Created by Jamshid Isoqov an 9/11/2022
interface DeleteNoteCategoryUseCase {
    suspend fun deleteNoteCategory(noteCategoryData: NoteCategoryData)
}
package uz.gita.notes_app.domain.usecase.note

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/6/2022
interface GetAllNoteByCategoryUseCase {
    fun getAllNotesByCategory(category: Int = 1): Flow<List<NoteData>>
}
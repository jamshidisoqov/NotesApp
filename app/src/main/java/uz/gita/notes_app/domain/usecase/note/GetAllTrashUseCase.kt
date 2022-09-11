package uz.gita.notes_app.domain.usecase.note

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/10/2022
interface GetAllTrashUseCase {
    fun getAllTrash(): Flow<List<NoteData>>
}
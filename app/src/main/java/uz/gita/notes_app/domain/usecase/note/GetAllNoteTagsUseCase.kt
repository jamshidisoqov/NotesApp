package uz.gita.notes_app.domain.usecase.note

import kotlinx.coroutines.flow.Flow

// Created by Jamshid Isoqov an 9/11/2022
interface GetAllNoteTagsUseCase {
    fun getAllNoteTags(): Flow<List<String>>
}
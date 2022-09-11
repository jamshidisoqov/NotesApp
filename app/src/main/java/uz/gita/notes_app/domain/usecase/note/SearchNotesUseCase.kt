package uz.gita.notes_app.domain.usecase.note

import kotlinx.coroutines.flow.Flow
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/9/2022
interface SearchNotesUseCase {
    fun searchNote(query: String): Flow<List<NoteData>>
}
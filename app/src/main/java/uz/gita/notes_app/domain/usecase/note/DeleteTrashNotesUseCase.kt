package uz.gita.notes_app.domain.usecase.note

// Created by Jamshid Isoqov an 9/10/2022
interface DeleteTrashNotesUseCase {
    suspend fun deleteAllTrashNotes()
}
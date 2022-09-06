package uz.gita.notes_app.domain.usecase.note

import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/6/2022
interface UpdateNoteUseCase {

   suspend fun updateNote(noteData: NoteData)

}
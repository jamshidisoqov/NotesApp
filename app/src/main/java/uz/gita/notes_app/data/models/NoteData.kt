package uz.gita.notes_app.data.models

import uz.gita.notes_app.data.source.local.entity.NoteEntity

// Created by Jamshid Isoqov an 9/6/2022
data class NoteData(
    val id: Int,
    val category: Int = 1,
    val title: String,
    val description: String,
    val pinned: Int = 0,
    val date: String
) {
    fun toNoteEntity() = NoteEntity(id, category, title, description, pinned, date)
}

package uz.gita.notes_app.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.gita.notes_app.data.source.local.entity.NoteEntity

// Created by Jamshid Isoqov an 9/6/2022
@Parcelize
data class NoteData(
    val id: Int = 0,
    val category: Int = 1,
    val title: String,
    val description: String,
    val pinned: Int = 0,
    val date: String
) : Parcelable {
    fun toNoteEntity() = NoteEntity(id, category, title, description, pinned, date)
}

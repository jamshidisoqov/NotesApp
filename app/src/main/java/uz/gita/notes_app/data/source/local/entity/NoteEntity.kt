package uz.gita.notes_app.data.source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import uz.gita.notes_app.data.models.NoteData

// Created by Jamshid Isoqov an 9/6/2022
@Entity(
    tableName = "notes", foreignKeys = [
        ForeignKey(
            entity = NoteCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: Int = 1,
    val title: String,
    val description: String,
    val pinned: Int = 0,
    val date: String
) {
    fun toNoteData() = NoteData(id, category, title, description, pinned, date)
}

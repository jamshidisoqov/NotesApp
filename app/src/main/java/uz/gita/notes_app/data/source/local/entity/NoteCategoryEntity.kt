package uz.gita.notes_app.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.notes_app.data.models.NoteCategoryData

// Created by Jamshid Isoqov an 9/6/2022
@Entity(tableName = "note_category")
data class NoteCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
) {
    fun toNoteCategoryData() = NoteCategoryData(id, name)
}

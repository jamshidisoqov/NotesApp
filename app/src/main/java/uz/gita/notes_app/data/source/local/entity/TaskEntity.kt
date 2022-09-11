package uz.gita.notes_app.data.source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import uz.gita.notes_app.data.models.TaskData

// Created by Jamshid Isoqov an 9/6/2022
@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = TaskCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: Int = 1,
    val title: String,
    val description: String,
    val is_checked: Int = 0,
    val date: String,
    val color: String,
    val status: Int = 1,
    val tag: String
) {
    fun toTaskData() =
        TaskData(id, category, title, description, is_checked, date, color, status, tag)
}
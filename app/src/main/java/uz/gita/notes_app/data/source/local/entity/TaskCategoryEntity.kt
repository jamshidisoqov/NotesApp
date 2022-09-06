package uz.gita.notes_app.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.notes_app.data.models.TaskCategoryData

// Created by Jamshid Isoqov an 9/6/2022
@Entity(tableName = "task_category")
data class TaskCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
) {
    fun toTaskCategoryData() = TaskCategoryData(id, name)
}

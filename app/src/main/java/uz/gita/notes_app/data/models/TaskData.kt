package uz.gita.notes_app.data.models

import uz.gita.notes_app.data.source.local.entity.TaskEntity

// Created by Jamshid Isoqov an 9/6/2022
data class TaskData(
    val id: Int,
    val category: Int = 1,
    val title: String,
    val description: String,
    val is_checked: Int = 0,
    val date: String
) {
    fun toTaskEntity() = TaskEntity(id, category, title, description, is_checked, date)
}
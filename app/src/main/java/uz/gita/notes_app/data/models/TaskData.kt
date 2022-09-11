package uz.gita.notes_app.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.gita.notes_app.data.source.local.entity.TaskEntity

// Created by Jamshid Isoqov an 9/6/2022
@Parcelize
data class TaskData(
    val id: Int = 0,
    val category: Int = 1,
    val title: String,
    val description: String,
    val is_checked: Int = 0,
    val date: String,
    val color: String = "#FFFFFF",
    val status: Int = 1,
    val tag: String
) : Parcelable {
    fun toTaskEntity() =
        TaskEntity(id, category, title, description, is_checked, date, color, status,tag)
}
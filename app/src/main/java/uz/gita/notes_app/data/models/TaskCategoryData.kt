package uz.gita.notes_app.data.models

import uz.gita.notes_app.data.source.local.entity.TaskCategoryEntity

// Created by Jamshid Isoqov an 9/6/2022
data class TaskCategoryData(
    val id: Int,
    val name: String
){
    fun toTaskCategoryEntity() = TaskCategoryEntity(id, name)
}
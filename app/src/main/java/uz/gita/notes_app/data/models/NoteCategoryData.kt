package uz.gita.notes_app.data.models

import uz.gita.notes_app.data.source.local.entity.NoteCategoryEntity

// Created by Jamshid Isoqov an 9/6/2022
data class NoteCategoryData(
    val id: Int,
    val name: String
){
    fun toNoteCategoryEntity() = NoteCategoryEntity(id, name)
}

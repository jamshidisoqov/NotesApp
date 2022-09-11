package uz.gita.notes_app.domain.usecase.task

// Created by Jamshid Isoqov an 9/10/2022
interface DeleteAllTrashTaskUseCase {
    suspend fun clearTrashTasks()
}
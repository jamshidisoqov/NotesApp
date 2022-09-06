package uz.gita.notes_app.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.notes_app.data.source.local.dao.NoteDao
import uz.gita.notes_app.data.source.local.dao.TaskDao
import uz.gita.notes_app.data.source.local.entity.NoteCategoryEntity
import uz.gita.notes_app.data.source.local.entity.NoteEntity
import uz.gita.notes_app.data.source.local.entity.TaskCategoryEntity
import uz.gita.notes_app.data.source.local.entity.TaskEntity

// Created by Jamshid Isoqov an 9/6/2022
@Database(
    entities = [NoteCategoryEntity::class, NoteEntity::class, TaskCategoryEntity::class, TaskEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun taskDao(): TaskDao

    companion object {

        private lateinit var instance: AppDatabase

        fun init(ctx: Context) {
            instance = Room.databaseBuilder(ctx, AppDatabase::class.java, "task_app.db")
                .build()
        }

        fun getInstance() = instance

    }

}
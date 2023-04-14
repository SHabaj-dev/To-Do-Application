package com.sbz.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sbz.todo.models.ToDo

@Database(
    entities = [ToDo::class],
    version = 1
)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun dao(): ToDoDao
}
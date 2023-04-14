package com.sbz.todo.database

import android.app.Application
import androidx.room.Room

class ToDoApplication : Application() {
    val database by lazy {
        Room.databaseBuilder(
            this,
            ToDoDatabase::class.java,
            "to_do_table"
        ).build()
    }
}
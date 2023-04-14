package com.sbz.todo.database

import androidx.room.*
import com.sbz.todo.models.ToDo

@Dao
interface ToDoDao {

    @Upsert
    suspend fun addItem(item: ToDo)


    @Query("SELECT * FROM to_do_table")
    suspend fun getAllItem(): List<ToDo>

    @Delete
    suspend fun deleteItem(item: ToDo)

}
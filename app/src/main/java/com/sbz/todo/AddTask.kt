package com.sbz.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sbz.todo.database.ToDoApplication
import com.sbz.todo.databinding.ActivityAddTaskBinding
import com.sbz.todo.models.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTask : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private val dao by lazy {
        (application as ToDoApplication)
            .database.dao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_task)

        binding.btnSaveTask.setOnClickListener {
            val task = binding.tvAddTask.text.toString()
            val item = ToDo(
                taskName = task,
                status = false
            )

            GlobalScope.launch(Dispatchers.IO) {
                dao.addItem(item)
            }
            onBackPressedDispatcher.onBackPressed()

        }
    }
}
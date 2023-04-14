package com.sbz.todo

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sbz.todo.adapters.ToDoAdapter
import com.sbz.todo.database.ToDoApplication
import com.sbz.todo.databinding.ActivityMainBinding
import com.sbz.todo.models.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dao by lazy {
        (application as ToDoApplication)
            .database.dao()
    }

    private lateinit var listOfTasks: List<ToDo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setPropertiesStatusBar()
        GlobalScope.launch(Dispatchers.IO) {
            listOfTasks = dao.getAllItem()
            Log.d("TASK_LIST", listOfTasks.toString())
            binding.rvMainActivity.adapter = ToDoAdapter(listOfTasks)
        }

        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddTask::class.java))
        }
    }

    private fun setPropertiesStatusBar() {
        window.statusBarColor = getColor(R.color.blue_light)
        supportActionBar?.title = "Tasks"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.blue_light)))
    }
}
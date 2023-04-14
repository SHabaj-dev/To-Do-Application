package com.sbz.todo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sbz.todo.R
import com.sbz.todo.database.ToDoApplication
import com.sbz.todo.models.ToDo
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ToDoAdapter(private val toDoList: List<ToDo>) :
    RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.MyViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoAdapter.MyViewHolder, position: Int) {
        val currentItem = toDoList[position]
        holder.toDoItem.text = currentItem.taskName
        holder.checkBox.isChecked = currentItem.status
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            currentItem.status = isChecked

        }
        holder.deleteBtn.setOnClickListener {
            deleteData(holder)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox = itemView.findViewById<CheckBox>(R.id.cb_isDone)
        val toDoItem = itemView.findViewById<TextView>(R.id.to_do_item)
        val deleteBtn = itemView.findViewById<ImageButton>(R.id.ib_delete)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun deleteData(holder: MyViewHolder) {
        val toDo = toDoList[holder.adapterPosition]
        val db = (holder.itemView.context.applicationContext as ToDoApplication).database
        val dao = db.dao()


        GlobalScope.launch {
            dao.deleteItem(toDo)
        }
    }
}
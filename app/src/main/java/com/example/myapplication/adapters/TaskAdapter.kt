package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.TaskModel

class TaskAdapter(
    private val taskList: ArrayList<TaskModel>,
    private val context: Context
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.task_design,
            parent, false
        )
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskDetail.text = taskList.get(position).taskDetail
        holder.taskPriority.setImageResource(taskList.get(position).isImportant)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskDetail: TextView = itemView.findViewById(R.id.taskDetail)
        val taskPriority: ImageView = itemView.findViewById(R.id.taskImportantButton)
    }
}



package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.TaskModel
import java.time.format.DateTimeFormatter

class TaskAdapter(
    private val taskList: ArrayList<TaskModel>,
    private val listener: com.example.myapplication.interfaces.OnItemClickListener,
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.task_design, parent, false
        )
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskDetail.text = taskList.get(position).taskDetail
        holder.taskPriority.setImageResource(taskList.get(position).isImportant)
        holder.date.text = taskList.get(position).dateTime.format(DateTimeFormatter.ofPattern("dd"))
        holder.month.text =
            taskList.get(position).dateTime.format(DateTimeFormatter.ofPattern("MMM"))
        holder.time.text =
            taskList.get(position).dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))

        holder.itemView.findViewById<ImageButton>(R.id.taskImportantButton).setOnClickListener {
            listener.itemClick(position)
        }

        holder.itemView.findViewById<TextView>(R.id.taskDetail).setOnClickListener {
            listener.textClick(position)
        }

    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskDetail: TextView = itemView.findViewById(R.id.taskDetail)
        val taskPriority: ImageView = itemView.findViewById(R.id.taskImportantButton)
        val date: TextView = itemView.findViewById(R.id.date)
        val month: TextView = itemView.findViewById(R.id.month)
        val time: TextView = itemView.findViewById(R.id.time)
    }
}
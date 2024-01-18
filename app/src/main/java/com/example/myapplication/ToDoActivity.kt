package com.example.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.adapters.TaskAdapter
import com.example.myapplication.dao.TaskDao
import com.example.myapplication.databinding.ActivityToDoBinding
import com.example.myapplication.databinding.TaskAddDialogBinding
import com.example.myapplication.databinding.TaskDesignBinding
import com.example.myapplication.models.TaskModel
import com.google.android.material.snackbar.Snackbar

class ToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToDoBinding
    private lateinit var taskDesignBinding: TaskDesignBinding
    private lateinit var taskAddDialogBinding: TaskAddDialogBinding

    private lateinit var tasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskList: ArrayList<TaskModel>

    private lateinit var db: AppDatabase
    private lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoBinding.inflate(layoutInflater)
        taskDesignBinding = TaskDesignBinding.inflate(layoutInflater)
        taskAddDialogBinding = TaskAddDialogBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_to_do)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "test")
            .allowMainThreadQueries().build()

        taskDao = db.taskDao()
        tasks = findViewById(R.id.tasks)
        tasks.layoutManager = LinearLayoutManager(this)
        taskList = taskDao.getAll() as ArrayList<TaskModel>
        taskAdapter = TaskAdapter(taskList, this)
        tasks.adapter = taskAdapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedTask: TaskModel = taskList.get(viewHolder.adapterPosition)
                val position = viewHolder.adapterPosition

                taskList.removeAt(position)
                taskDao.remove(deletedTask)
                Log.d("POSITION", position.toString())
                taskAdapter.notifyItemRemoved(position)

                Snackbar.make(tasks, "Deleted " + deletedTask.taskDetail, Snackbar.LENGTH_LONG)
                    .setAction(
                        "Undo"
                    ) {
                        taskList.add(position, deletedTask)
                        taskDao.insertAll(deletedTask)
                        taskAdapter.notifyDataSetChanged()
                    }.show()
            }

        }).attachToRecyclerView(tasks)
    }

    fun addTask(view: View) {
        val builder = AlertDialog.Builder(this)
        val customDialog: View = layoutInflater.inflate(R.layout.task_add_dialog, null)
        builder.setView(customDialog)

        builder.setCancelable(true)
        builder.setPositiveButton("Add Task") { dialog: DialogInterface?, which: Int ->
            val newTask = TaskModel(
                taskDao.getCount() + 1,
                customDialog.findViewById<EditText>(R.id.taskData).text.toString(),
                R.drawable.bookmark_outlined
            )
            taskList.add(newTask)
            taskDao.insertAll(newTask)
            taskAdapter.notifyDataSetChanged()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
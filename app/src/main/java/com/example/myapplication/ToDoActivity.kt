package com.example.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.example.myapplication.adapters.TaskAdapter
import com.example.myapplication.dao.TaskDao
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityToDoBinding
import com.example.myapplication.databinding.TaskAddDialogBinding
import com.example.myapplication.databinding.TaskDesignBinding
import com.example.myapplication.interfaces.OnItemClickListener
import com.example.myapplication.models.TaskModel
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime

class ToDoActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityToDoBinding
    private lateinit var taskDesignBinding: TaskDesignBinding
    private lateinit var taskAddDialogBinding: TaskAddDialogBinding

    private lateinit var tasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskList: ArrayList<TaskModel>

    private lateinit var db: AppDatabase
    private lateinit var taskDao: TaskDao

    private lateinit var builder: AlertDialog.Builder
    private lateinit var customDialog: View

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

        val layoutViewBtn = findViewById<ImageButton>(R.id.layoutBtn)
        layoutViewBtn.tag = R.drawable.gridview_outline
        tasks.layoutManager = LinearLayoutManager(this)

        layoutViewBtn.setOnClickListener {
            Log.d("LAYOUT STATUS", "VIEW ACTIVE")

            if (layoutViewBtn.tag == R.drawable.listview_outline) {
                tasks.layoutManager = LinearLayoutManager(this)
                layoutViewBtn.setImageResource(R.drawable.gridview_outline)
                layoutViewBtn.tag = R.drawable.gridview_outline
            } else {
                tasks.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                layoutViewBtn.setImageResource(R.drawable.listview_outline)
                layoutViewBtn.tag = R.drawable.listview_outline
            }
        }

        val spinner = findViewById<Spinner>(R.id.taskType)

        if (spinner != null) {
            spinner.adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.task_type)
            )
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                when (id) {
                    0L -> {
                        taskList = taskDao.getAll() as ArrayList<TaskModel>
                        taskAdapter = TaskAdapter(taskList, this@ToDoActivity)
                        tasks.adapter = taskAdapter
                    }

                    1L -> {
                        taskList = taskDao.getStarred() as ArrayList<TaskModel>
                        taskAdapter = TaskAdapter(taskList, this@ToDoActivity)
                        tasks.adapter = taskAdapter
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

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
        val addTaskFunc = {
            builder.setPositiveButton("Add Task") { dialog: DialogInterface?, which: Int ->

                val newTask: TaskModel = TaskModel(
                    taskDao.getCount() + 1,
                    customDialog.findViewById<EditText>(R.id.taskData).text.toString(),
                    R.drawable.bookmark_outlined,
                    LocalDateTime.now()
                )

                taskDao.insertAll(newTask)
                taskList.add(newTask)
                taskAdapter.notifyDataSetChanged()
            }
            builder.create().show()
        } //lambda expression
        showDialog(addTaskFunc)
    }

    override fun itemClick(position: Int) {
        if (taskList[position].isImportant == R.drawable.bookmark_outlined) {
            changeTaskPriority(position, R.drawable.bookmark_filled) //change image
        } else {
            changeTaskPriority(position, R.drawable.bookmark_outlined) //change image
        }
    }

    override fun textClick(position: Int) {
        val updateTaskFunc = {
            val taskItem = taskList[position]
            customDialog.findViewById<EditText>(R.id.taskData).setText(taskItem.taskDetail)

            builder.setPositiveButton("Update Task") { dialog: DialogInterface?, which: Int ->
                taskDao.updateTask(
                    TaskModel(
                        taskItem.id,
                        customDialog.findViewById<EditText>(R.id.taskData).text.toString(),
                        taskItem.isImportant,
                        taskItem.dateTime
                    )
                )
                taskItem.taskDetail =
                    customDialog.findViewById<EditText>(R.id.taskData).text.toString()
                taskAdapter.notifyItemChanged(position)
            }
            builder.create().show()
        } //lambda expression
        showDialog(updateTaskFunc)
    }

    private fun changeTaskPriority(position: Int, taskPriority: Int) {
        taskDao.updateTask(
            TaskModel(
                taskList[position].id,
                taskList[position].taskDetail,
                taskPriority,
                taskList[position].dateTime
            )
        )
        taskList[position].isImportant = taskPriority
        taskAdapter.notifyItemChanged(position)
    }

    private fun showDialog(func: () -> Unit) {
        builder = AlertDialog.Builder(this)
        customDialog = layoutInflater.inflate(R.layout.task_add_dialog, null)
        builder.setView(customDialog)
        builder.setCancelable(true)
        func() //lambda
    }
}
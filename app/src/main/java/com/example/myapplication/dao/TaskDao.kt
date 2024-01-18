package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.models.TaskModel

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskmodel")
    fun getAll(): List<TaskModel>

    @Query("SELECT MAX(id) FROM taskmodel")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tasks: TaskModel)

    @Delete
    fun remove(task: TaskModel)
}
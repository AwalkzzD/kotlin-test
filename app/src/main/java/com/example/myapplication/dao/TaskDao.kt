package com.example.myapplication.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.models.TaskModel

@Dao
interface TaskDao {

    //    remove
    @Query("SELECT * FROM taskmodel")
    fun getAll(): List<TaskModel>

    @Query("SELECT * FROM taskmodel")
    fun getAllPaged(): PagingSource<Int, TaskModel>

    //    remove
    @Query("SELECT * FROM taskmodel WHERE is_important == 2131230809")
    fun getStarred(): List<TaskModel>

    @Query("SELECT * FROM taskmodel WHERE is_important == 2131230809")
    fun getStarredPaged(): PagingSource<Int, TaskModel>

    @Query("SELECT MAX(id) FROM taskmodel")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tasks: TaskModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(task: TaskModel)

    @Delete
    fun remove(task: TaskModel)
}
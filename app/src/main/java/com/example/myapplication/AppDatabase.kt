package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.dao.TaskDao
import com.example.myapplication.models.TaskModel

@Database(entities = [TaskModel::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
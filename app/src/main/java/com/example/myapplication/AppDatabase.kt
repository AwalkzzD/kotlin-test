package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.dao.TaskDao
import com.example.myapplication.models.TaskModel

@Database(entities = [TaskModel::class], version = 7)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
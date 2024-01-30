package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.Converters
import com.example.myapplication.dao.ItemDao
import com.example.myapplication.dao.TaskDao
import com.example.myapplication.models.ItemDataModel
import com.example.myapplication.models.TaskModel

@Database(entities = [TaskModel::class], version = 7)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

@Database(entities = [ItemDataModel::class], version = 1)
abstract class ItemPagingDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
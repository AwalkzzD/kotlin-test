package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "task_detail") val taskDetail: String,
    @ColumnInfo(name = "is_important") val isImportant: Int
)
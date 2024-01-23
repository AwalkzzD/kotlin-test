package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class TaskModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "task_detail") var taskDetail: String,
    @ColumnInfo(name = "is_important") var isImportant: Int,
    @ColumnInfo(name = "datetime") val dateTime: LocalDateTime
)
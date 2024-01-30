package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemDataModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "data_content") var itemContent: String
)

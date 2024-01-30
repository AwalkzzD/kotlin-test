package com.example.myapplication.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.models.ItemDataModel

@Dao
interface ItemDao {

    @Query("SELECT * FROM itemdatamodel")
    fun getAllPaged(): PagingSource<Int, ItemDataModel>

    @Insert
    suspend fun insert(item: ItemDataModel)

    @Update
    suspend fun update(item: ItemDataModel)

    @Delete
    suspend fun remove(item: ItemDataModel)
}
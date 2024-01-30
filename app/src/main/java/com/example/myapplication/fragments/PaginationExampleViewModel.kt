package com.example.myapplication.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.myapplication.database.ItemPagingDatabase

class PaginationExampleViewModel(app: Application) : AndroidViewModel(app) {
    private val itemDao =
        Room.databaseBuilder(app, ItemPagingDatabase::class.java, "testdb").build()
            .itemDao()

    val items = Pager(
        PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            prefetchDistance = 0,
            maxSize = 30
        )
    ) {
        itemDao.getAllPaged()
    }.flow
}
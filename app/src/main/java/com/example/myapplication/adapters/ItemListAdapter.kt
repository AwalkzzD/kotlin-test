package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.models.ItemDataModel

class ItemListAdapter() : PagingDataAdapter<ItemDataModel, ItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemDataModel>() {
            override fun areItemsTheSame(oldItem: ItemDataModel, newItem: ItemDataModel) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: ItemDataModel, newItem: ItemDataModel) = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemDataModel: ItemDataModel? = getItem(position)
        holder.bindTo(itemDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }
}
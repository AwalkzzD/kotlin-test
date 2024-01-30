package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.ItemDataModel

class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_item, parent, false)
) {

    private val contentView = itemView.findViewById<TextView>(R.id.listItemContent)

    var itemDataModel: ItemDataModel? = null

    fun bindTo(itemDataModel: ItemDataModel?) {
        this.itemDataModel = itemDataModel
        contentView.text = itemDataModel?.id.toString() ?: "null"
    }
}
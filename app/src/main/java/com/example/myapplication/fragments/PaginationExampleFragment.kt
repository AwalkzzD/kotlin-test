package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.adapters.ItemListAdapter
import com.example.myapplication.database.ItemPagingDatabase
import com.example.myapplication.models.ItemDataModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PaginationExampleFragment : Fragment() {

    private lateinit var itemListAdapter: ItemListAdapter

    companion object {
        fun newInstance() = PaginationExampleFragment()
    }

    private lateinit var viewModel: PaginationExampleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pagination_example, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populateDB()
        viewModel = ViewModelProvider(this).get(PaginationExampleViewModel::class.java)

//        viewModel =
//            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(
//                PaginationExampleViewModel::class.java
//            )

        itemListAdapter = ItemListAdapter()
        requireView().findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = itemListAdapter
        }

        lifecycleScope.launch {
            viewModel.items.collectLatest {
                itemListAdapter.submitData(it)
            }
        }
    }

    fun populateDB() {
        val itemDao =
            Room.databaseBuilder(requireContext(), ItemPagingDatabase::class.java, "testdb").build()
                .itemDao()

        GlobalScope.launch {
            for (i in 1..100) {
                itemDao.insert(ItemDataModel(id = 0, itemContent = "Item $i"))
            }
        }
    }

}
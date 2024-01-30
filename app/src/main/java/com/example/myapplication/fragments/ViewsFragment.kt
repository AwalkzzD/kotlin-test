package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.ToDoActivity
import com.example.myapplication.databinding.FragmentViewsBinding


class ViewsFragment : Fragment() {
    lateinit var binding: FragmentViewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.todoBtn.setOnClickListener {
            startActivity(Intent(activity, ToDoActivity::class.java))
        }

        binding.alarmBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewsFragment_to_alarmFragment)
        }

        binding.tutorialBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewsFragment_to_tutorialFragment)
        }

        binding.tabLayoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewsFragment_to_tabLayoutFragment)
        }

        binding.pagingBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewsFragment_to_paginationExampleFragment)
        }
    }
}
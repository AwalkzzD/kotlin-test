package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.adapters.ViewPagerAdapter
import com.example.myapplication.databinding.FragmentTabLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator


class TabLayoutFragment : Fragment() {
    lateinit var binding: FragmentTabLayoutBinding
    private val animalsArray = arrayOf(
        "Cow",
        "Cat",
        "Dog",
        "Others"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter =
            activity?.let { ViewPagerAdapter(it.supportFragmentManager, lifecycle) }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = animalsArray[position]
        }.attach()
    }
}
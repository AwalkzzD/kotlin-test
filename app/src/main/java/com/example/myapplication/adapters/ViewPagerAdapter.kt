package com.example.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.fragments.animalfragments.CatFragment
import com.example.myapplication.fragments.animalfragments.CowFragment
import com.example.myapplication.fragments.animalfragments.DogFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return CowFragment()
            1 -> return CatFragment()
        }
        return DogFragment()
    }
}
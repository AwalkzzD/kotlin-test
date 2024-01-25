package com.example.myapplication.fragments.animalfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCowBinding

class CowFragment : Fragment() {
    lateinit var binding: FragmentCowBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCowBinding.inflate(layoutInflater)
        return binding.root
    }
}
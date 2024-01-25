package com.example.myapplication.fragments.animalfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCatBinding

class CatFragment : Fragment() {
    lateinit var binding: FragmentCatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCatBinding.inflate(layoutInflater)
        return binding.root
    }

}
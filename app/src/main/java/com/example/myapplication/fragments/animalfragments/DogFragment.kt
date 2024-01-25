package com.example.myapplication.fragments.animalfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentDogBinding

class DogFragment : Fragment() {

    lateinit var binding: FragmentDogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogBinding.inflate(layoutInflater)
        return binding.root
    }

}
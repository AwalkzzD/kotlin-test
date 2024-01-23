package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment() {
    lateinit var binding: FragmentAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.time.visibility = View.INVISIBLE

        binding.timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            binding.time.visibility = View.VISIBLE
            binding.time.text = "$hourOfDay:$minute"
        }
    }

}
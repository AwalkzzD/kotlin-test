package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentTutorialBinding

class TutorialFragment : Fragment() {

    lateinit var binding: FragmentTutorialBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorialBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.courseGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = group.findViewById(checkedId)
            if (radio.isChecked) {
                binding.reviewSelectionBtn.visibility = View.VISIBLE
                if (radio == binding.mad) {
                    binding.courseModulesMAD.visibility = View.VISIBLE
                    binding.courseModulesWD.visibility = View.INVISIBLE
                } else {
                    binding.courseModulesMAD.visibility = View.INVISIBLE
                    binding.courseModulesWD.visibility = View.VISIBLE
                }
            }
        }

        binding.reviewSelectionBtn.setOnClickListener {
            val selectedModules = StringBuilder()

            if (binding.dbWD.isChecked) {
                selectedModules.appendLine("${binding.dbWD.text}")
            }

            if (binding.dbMAD.isChecked) {
                selectedModules.appendLine("${binding.dbMAD.text}")
            }

            if (binding.cross.isChecked) {
                selectedModules.appendLine("${binding.cross.text}")
            }

            if (binding.nativeAndroid.isChecked) {
                selectedModules.appendLine("${binding.nativeAndroid.text}")
            }

            if (binding.mern.isChecked) {
                selectedModules.appendLine("${binding.mern.text}")
            }

            if (binding.mean.isChecked) {
                selectedModules.appendLine("${binding.mean.text}")
            }

            Toast.makeText(context, selectedModules.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
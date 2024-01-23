package com.example.myapplication.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Calendar

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var bottomDialog: BottomSheetDialog
    private var isCameraSelected = false
    private lateinit var datePicker: DatePicker

    private val galleryLauncher =
        this.registerForActivityResult(ActivityResultContracts.GetContent()) {
            val galleryUri = it
            try {
                binding.profilePhoto.setImageURI(galleryUri)
//                binding.profilePhoto.setImageBitmap(null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private val cameraLauncher =
        this.registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            val cameraBitap = it
            try {
                binding.profilePhoto.setImageURI(null)
                binding.profilePhoto.setImageBitmap(cameraBitap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private val permissionLauncher =
        this.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission Accepted: Do something
                if (isCameraSelected) {
                    cameraLauncher.launch()
                } else {
                    galleryLauncher.launch("image/*")
                }

                bottomDialog.hide()
            } else {
                // Permission Denied: Do something
                Toast.makeText(context, "User denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dob.visibility = View.INVISIBLE
        binding.editImage.setOnClickListener {
            bottomDialog = BottomSheetDialog(view.context)
            val bottomView = layoutInflater.inflate(R.layout.bottomsheet, null)

            val closeBtn = bottomView.findViewById<ImageButton>(R.id.closeBtn)
            val galleryBtn = bottomView.findViewById<ImageButton>(R.id.galleryBtn)
            val cameraBtn = bottomView.findViewById<ImageButton>(R.id.cameraBtn)

            bottomDialog.setCancelable(false)
            bottomDialog.setContentView(bottomView)
            bottomDialog.show()

            closeBtn.setOnClickListener {
                bottomDialog.dismiss()
            }

            galleryBtn.setOnClickListener {
                galleryLauncher.launch("image/*")
                bottomDialog.hide()
            }

            cameraBtn.setOnClickListener {
                when (PackageManager.PERMISSION_GRANTED) {
                    activity?.let { it1 ->
                        ContextCompat.checkSelfPermission(
                            it1,
                            Manifest.permission.CAMERA
                        )
                    } -> {
                        cameraLauncher.launch()
                        bottomDialog.hide()
                    }

                    else -> {
                        isCameraSelected = true
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
            }
        }

        datePicker = binding.datePicker
        val today = Calendar.getInstance()
        datePicker.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            binding.dob.visibility = View.VISIBLE
            val month = month + 1
            binding.dob.text = "$day/$month/$year"
        }
    }
}
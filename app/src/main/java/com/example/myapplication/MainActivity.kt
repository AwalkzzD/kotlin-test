package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

const val USERNAME = "username"
const val PASSWORD = "password"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.signInBtn.setOnClickListener { verifyUser() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(USERNAME, binding.username.text.toString())
        outState.putString(PASSWORD, binding.password.text.toString())

        Log.d("SAVE INSTANCE STATE DESTROY", "VALUES STORED")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val userName: String = savedInstanceState.getString(USERNAME, "")
        Log.d("SAVE INSTANCE STATE CREATE", "VALUES FETCHED $userName")

    }

    /*
    override fun onPause() {
    super.onPause()
    Log.d("MAIN ACTIVITY", "Screen Paused")
    }

    override fun onStop() {
    super.onStop()
    Log.d("MAIN ACTIVITY", "Screen Stopped")
    }

    override fun onDestroy() {
    super.onDestroy()
    Log.d("MAIN ACTIVITY", "Screen Destroyed")
    }

    override fun onStart() {
    super.onStart()
    Log.d("MAIN ACTIVITY", "Screen Visible")
    }
    */

    private fun verifyUser() {
        if (binding.username.text.toString() == "awalkzz" && binding.password.text.toString() == "devarshi") {
            Toast.makeText(this, "User is verified", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "User is not verified", Toast.LENGTH_SHORT).show()
        }
    }
}
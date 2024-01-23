package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.widget.ArrayAdapter
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

        binding.username.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.email_ids)
            )
        )

        verifyEmail()
        binding.signInBtn.setOnClickListener { verifyUser() }
    }
    /*
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
        }*/

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
//        val intent = Intent(this, HomeActivity::class.java)
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)

        /*if (binding.username.text.toString() == "1@1.com" && binding.password.text.toString() == "1") {
            Toast.makeText(this, "User is verified", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "User is not verified", Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun verifyEmail() {
        binding.username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString().trim()
                if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
                    binding.username.error = "Please enter a valid email address"
                    binding.signInBtn.isEnabled = false
                } else {
                    binding.username.error = null
                    binding.signInBtn.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
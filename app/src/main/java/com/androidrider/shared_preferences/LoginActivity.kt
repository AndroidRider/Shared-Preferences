package com.androidrider.shared_preferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidrider.shared_preferences.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var  binding: ActivityLoginBinding

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name),MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {

            // User is already logged in, navigate to another activity or perform necessary actions
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }else {

            binding.btnLogin.setOnClickListener {

                val username = binding.edtUserName.text.toString()
                val password = binding.edtPassword.text.toString()

                binding.edtUserName.text?.clear()
                binding.edtPassword.text?.clear()

                // For simplicity, you can hardcode a username and password
                val validUsername = "user"
                val validPassword = "password"

                if (username == validUsername && password == validPassword) {
                    editor.putBoolean("isLoggedIn", true)
                    editor.apply()

                    // Login successful, navigate to another activity or perform necessary actions
                    startActivity(Intent(this,MainActivity::class.java))
                    Toast.makeText(this, "Logged In Successful!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {

                    // Login failed, show an error message
                    Toast.makeText(this, "Invalid username & password!", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}
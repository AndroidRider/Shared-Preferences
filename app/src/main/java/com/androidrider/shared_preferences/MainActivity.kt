package com.androidrider.shared_preferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidrider.shared_preferences.databinding.ActivityLoginBinding
import com.androidrider.shared_preferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name),MODE_PRIVATE)
        editor = sharedPreferences.edit()

        binding.apply {

            btnSave.setOnClickListener {
                val userName = edtUserName.text.toString()
                val password = edtPassword.text.toString()

                edtUserName.text?.clear()
                edtPassword.text?.clear()

                editor.apply {
                    putString("user_name",userName)
                    putString("password",password)
                    apply()
                }
                Toast.makeText(this@MainActivity, "Data Successfully saved!", Toast.LENGTH_SHORT).show()
            }

            btnLoadData.setOnClickListener {
                val userName = sharedPreferences.getString("user_name", null)
                val password = sharedPreferences.getString("password", null)

                tvUserName.text = userName
                tvPassword.text = password
            }
        }


        //Log out from the application. Remember to clear the shared preferences when you log out of the app.
        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            sharedPreferences.edit().clear().apply()
            Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
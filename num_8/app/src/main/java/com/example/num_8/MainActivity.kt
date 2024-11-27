package com.example.num_8

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var themeSwitchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        themeSwitchButton = findViewById(R.id.button)


        sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE)


        val isNightMode = sharedPreferences.getBoolean("isNightMode", false)
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


        themeSwitchButton.setOnClickListener {
            toggleTheme()
        }
    }

    private fun toggleTheme() {

        val currentNightMode = AppCompatDelegate.getDefaultNightMode()


        if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            saveThemePreference(false)
        } else {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            saveThemePreference(true)
        }
    }

    private fun saveThemePreference(isNightMode: Boolean) {

        val editor = sharedPreferences.edit()
        editor.putBoolean("isNightMode", isNightMode)
        editor.apply()
    }
}

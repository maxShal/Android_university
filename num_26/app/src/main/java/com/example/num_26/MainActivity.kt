package com.example.num_26

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager



class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        // Инициализация SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        // Применяем настройки при запуске
        applySettings()

        // Кнопка для открытия настроек
        button.setOnClickListener {
            showSettings()
        }
    }

    override fun onResume() {
        super.onResume()
        // Применяем настройки каждый раз, когда активность становится видимой
        applySettings()
    }

    private fun showSettings() {
        val intent = Intent(
            this,
            MyPreferenceActivity::class.java
        )
        startActivity(intent)
    }


    private fun applySettings() {
        // Получаем текущие значения из SharedPreferences
        val fontSize = sharedPreferences.getString("test_size", "14")?.toFloat() ?: 14f
        val fontStyle = sharedPreferences.getString("test_style", "Обычный")

        // Применение размера шрифта
        textView.textSize = fontSize

        // Применение стиля шрифта
        when (fontStyle) {
            "Обычный" -> textView.setTypeface(null, android.graphics.Typeface.NORMAL)
            "Полужирный" -> textView.setTypeface(null, android.graphics.Typeface.BOLD)
            "Курсив" -> textView.setTypeface(null, android.graphics.Typeface.ITALIC)
            "Полужирный+Курсив" -> textView.setTypeface(null, android.graphics.Typeface.BOLD_ITALIC)
        }
    }

}
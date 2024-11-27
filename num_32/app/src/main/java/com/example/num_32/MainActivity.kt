package com.example.num_32

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим кнопку и добавляем слушатель
        val showDialogButton: Button = findViewById(R.id.button_show_dialog)
        showDialogButton.setOnClickListener {
            // Показываем наш DialogFragment
            val dialog = MyDialogFragment()
            dialog.show(supportFragmentManager, "MyDialogFragment")
        }
    }
}

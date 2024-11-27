package com.example.num_20

import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Убедитесь, что в вашем XML есть LinearLayout с id root_layout
        val rootLayout = findViewById<LinearLayout>(R.id.root_layout)

        // Создаем EditText программно
        val ipt = EditText(this)
        ipt.inputType = InputType.TYPE_CLASS_PHONE
        // Добавляем EditText в layout
        rootLayout.addView(ipt)

        // Устанавливаем фокус на EditText
        ipt.requestFocus()

        // Показываем клавиатуру
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(ipt, InputMethodManager.SHOW_IMPLICIT)
        onClick(ipt)
    }

    fun onClick(view: View?) {
        // Получаем текущий язык ввода
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val ims = imm.currentInputMethodSubtype
        val localeString = ims?.locale ?: ""
        val locale = Locale(localeString)
        val currentLanguage = locale.displayLanguage

        // Выводим текущий язык
        Toast.makeText(applicationContext, currentLanguage, Toast.LENGTH_SHORT).show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true // блокируем стандартное поведение меню
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Вызываем onBackPressed вручную
            onBackPressed()
            return true // блокируем стандартное поведение кнопки назад
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        // Запрос на подтверждение выхода
        AlertDialog.Builder(this).apply {
            setTitle("Подтверждение")
            setMessage("Вы уверены, что хотите выйти из программы?")
            setPositiveButton("Таки да") { _, _ ->
                super.onBackPressed()
            }
            setNegativeButton("Нет") { _, _ ->
                // если пользователь нажал "Нет", то возвращаемся в активити
                Toast.makeText(this@MainActivity, "Спасибо!", Toast.LENGTH_LONG).show()
            }
            setCancelable(true)
        }.create().show()
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            // Выводим сообщение при долгом нажатии на кнопку меню
            Toast.makeText(this@MainActivity, "Long press", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onKeyLongPress(keyCode, event)
    }
}

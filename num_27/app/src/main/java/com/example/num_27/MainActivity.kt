package com.example.num_27


import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    private val FILENAME = "sample.txt" // имя файла

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open -> {
                openFile(FILENAME)
                true
            }
            R.id.action_save -> {
                saveFile(FILENAME)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Метод для открытия файла
    private fun openFile(fileName: String) {
        try {
            val textFromFile =
                File(applicationContext.filesDir, fileName)
                    .bufferedReader()
                    .use { it.readText() }
            editText.setText(textFromFile)
            Toast.makeText(applicationContext, "Файл открыт", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Ошибка при открытии файла", Toast.LENGTH_SHORT).show()
        }
    }

    // Метод для сохранения файла
    private fun saveFile(fileName: String) {
        try {
            applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(editText.text.toString().toByteArray())
            }
            Toast.makeText(applicationContext, "Файл сохранён", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Ошибка при сохранении файла", Toast.LENGTH_SHORT).show()
        }
    }
}

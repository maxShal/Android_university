package com.example.fifth

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val secondText: TextView = findViewById(R.id.textView2)
        val thirdText: TextView = findViewById(R.id.textView3)
        val forthText: TextView = findViewById(R.id.textView5)
        val rightBottomImage: ImageView = findViewById(R.id.imageView3)

        rightBottomImage.setOnClickListener{
            val phrases = listOf(
                "Уже 6 часов утра, Наташ",
                "Вставай, мы там всё уронили",
                "Мы уронили вообще всё, Наташ, честно",
                "Наташ, ты чё опять лежишь?",
                "Часики-то тикают",
                "Мужика-то хоть нашла себе?",
                "Уже дитачек пора рожать вообще-то")
            val shuffledList = phrases.shuffled()

            secondText.text = shuffledList[0]
            thirdText.text = shuffledList[1]
            forthText.text = shuffledList[2]
        }
    }
}
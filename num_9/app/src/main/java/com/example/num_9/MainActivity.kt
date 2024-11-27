package com.example.num_9

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {

            val inflater = layoutInflater
            val container: View = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_root))


            val text: TextView = container.findViewById(R.id.toast_text)
            text.text = "Засчитайте лабораторную пожалуйста."


            val toast = Toast(applicationContext)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = container
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
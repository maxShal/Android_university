package com.example.num_7

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Surface
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val first_button : Button = findViewById(R.id.button1)
        val second_button : Button = findViewById(R.id.button2)
        val third_button : Button = findViewById(R.id.button3)
        first_button.setOnClickListener{
            Toast.makeText(this, getOrientation(), Toast.LENGTH_SHORT).show()}

        second_button.setOnClickListener{
            Toast.makeText(this, getRotateOrientation(), Toast.LENGTH_SHORT).show()}

        third_button.setOnClickListener{
            val orientation = getOrientation();
            if(orientation == "Портретная ориентация")
            {
                setScreenOrientation(false)
            }else{
                setScreenOrientation(true)
            }
        }
    }

    private fun getOrientation(): String{
        return when(resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT -> "Портретная ориентация"
            Configuration.ORIENTATION_LANDSCAPE -> "Альбомная ориентация"
            else -> ""
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getRotateOrientation(): String {
        val rotation = this.display?.rotation ?: Surface.ROTATION_0

        return when (rotation) {
            Surface.ROTATION_0 -> "Не поворачивали"
            Surface.ROTATION_90 -> "Повернули на 90 градусов по часовой стрелке"
            Surface.ROTATION_180 -> "Повернули на 180 градусов"
            Surface.ROTATION_270 -> "Повернули на 90 градусов против часовой стрелки"
            else -> "Не понятно"
        }
    }

    private fun setScreenOrientation(isPortrait: Boolean) {
        requestedOrientation = if (isPortrait) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

}
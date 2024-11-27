package com.example.num_13

import android.content.ContentResolver
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val screenMetricsTextView: TextView = findViewById(R.id.screenMetrics)
        val brightnessSeekBar: SeekBar = findViewById(R.id.brightnessSeekBar)


        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val densityDpi = displayMetrics.densityDpi
        val density = displayMetrics.density
        val scaledDensity = displayMetrics.scaledDensity


        val metricsInfo = """
            Ширина: $screenWidth px
            Высота: $screenHeight px
            Плотность DPI: $densityDpi
            Плотность: $density
            Масштабированная плотность: $scaledDensity
        """.trimIndent()
        screenMetricsTextView.text = metricsInfo


        if (!Settings.System.canWrite(this)) {

            ActivityCompat.requestPermissions(this, arrayOf(Settings.ACTION_MANAGE_WRITE_SETTINGS), 0)
        }


        val currentBrightness = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 125)
        brightnessSeekBar.progress = currentBrightness


        brightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (Settings.System.canWrite(this@MainActivity)) {
                    try {

                        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, progress)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity, "Failed to change brightness", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Permission not granted to change settings", Toast.LENGTH_LONG).show()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}

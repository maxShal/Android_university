package com.example.num_28

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private val timeBroadcastReceiver = TimeBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            registerReceiver(
                timeBroadcastReceiver, IntentFilter(
                    "android.intent.action.TIME_TICK"
                )
            )
            Toast.makeText(
                getApplicationContext(), "Приёмник включен",
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(timeBroadcastReceiver)
        Toast.makeText(
            getApplicationContext(),
            "Приёмник выключён", Toast.LENGTH_SHORT
        )
            .show();
    }
}
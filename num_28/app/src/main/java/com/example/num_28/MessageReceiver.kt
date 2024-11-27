package com.example.num_28

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MessageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MessageReceiver", "Получено широковещательное сообщение")
        val message = intent?.getStringExtra("message")
        Toast.makeText(context, message ?: "Сообщение отсутствует", Toast.LENGTH_LONG).show()
    }
}


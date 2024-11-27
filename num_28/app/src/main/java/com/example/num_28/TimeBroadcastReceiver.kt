package com.example.num_28

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date

class TimeBroadcastReceiver : BroadcastReceiver() {

    @SuppressLint("SimpleDateFormat")
    override fun onReceive(context: Context?, intent: Intent?) {
        val msgStr = StringBuilder("Текущее время: ")
        val formatter: Format = SimpleDateFormat("hh:mm:ss a")
        msgStr.append(formatter.format(Date()))
        Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show()
    }
}
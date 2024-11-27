package com.example.num_32

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Это сообщение в диалоговом окне")
                .setTitle("Простой диалог")
                .setPositiveButton("OK") { dialog, id ->
                    // Пользователь нажал OK
                }
                .setNegativeButton("Отмена") { dialog, id ->
                    // Пользователь нажал Отмена
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
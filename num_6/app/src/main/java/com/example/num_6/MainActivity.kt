package com.example.num_6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private val  questionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data = result.data
            val answer = data?.getStringExtra(THIEF)
            val textViewInto: TextView = findViewById(R.id.textview_into)
            textViewInto.text = answer
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button_choose)
        button.setOnClickListener {
            val questionIntent = Intent(this@MainActivity,
                SecondActivity::class.java)
            questionLauncher.launch(questionIntent)
        }
    }

}
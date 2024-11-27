package com.example.num_6

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val THIEF = "com.example.num_6.THIEF"

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val radioGroup : RadioGroup = findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener{_, optionId ->
            val answerIntent = Intent()
            when (optionId){
                R.id.radio_dog -> answerIntent.putExtra(THIEF,"Пёс")
                R.id.radio_cat -> answerIntent.putExtra(THIEF,"Кот")
                R.id.radio_crow -> answerIntent.putExtra(THIEF,"Ворон")
            }
            setResult(RESULT_OK, answerIntent)
            finish()
        }
    }
}
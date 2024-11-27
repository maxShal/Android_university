package com.example.num_15

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meterRadioButton: RadioButton = findViewById(R.id.radioButton)
        val inputEditText: EditText = findViewById(R.id.editTextText)
        val parrotRadioButton: RadioButton = findViewById(R.id.radioButton2)

        meterRadioButton.setOnCheckedChangeListener { _, _ ->
            parrotRadioButton.isChecked = false

        }

        parrotRadioButton.setOnCheckedChangeListener { _, _ ->
            meterRadioButton.isChecked = false

        }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            if (inputEditText.text.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Введите длину кота",
                    Toast.LENGTH_LONG).show()
            }
            else {
                val inputValue = inputEditText.text.toString().toFloat()

                if (meterRadioButton.isChecked) {
                    inputEditText.setText(convertParrotToMeter(inputValue).toString())
                } else {
                    inputEditText.setText(convertMeterToParrot(inputValue).toString())
                }
            }
        }
    }

    // Конвертируем в метры
    private fun convertParrotToMeter(parrot: Float): Float = (parrot / 7.6).toFloat()

    // Конвертируем в попугаи
    private fun convertMeterToParrot(meter: Float): Float = (meter * 7.6).toFloat()
}
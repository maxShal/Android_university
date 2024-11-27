package com.example.num_23

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val titles = arrayOf(
        "00. Начало",
        "01. Чем кормить кота",
        "02. Как гладить кота",
        "03. Как спит кот",
        "04. Как играть с котом",
        "05. Как разговаривать с котом",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, titles)
        listView.isTextFilterEnabled = true

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { a, v, position, id ->
                val intent = Intent()
                intent.setClass(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("title", position)

                startActivity(intent)
            }
    }
}
package com.example.musicplayertataloo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {
    lateinit var listView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        listView = findViewById(R.id.list_view)

        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = CustomAdapter(this,MainActivity.musics)
    }
}
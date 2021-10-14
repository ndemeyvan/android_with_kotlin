package com.bee_studio.learn_recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    var programmingLanguage = listOf<String>()
    var programminDescription = listOf<String>()
    lateinit var adapter : RecyclerViewAdapter
    lateinit var recyclerView: RecyclerView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView = findViewById(R.id.recyclerview)
        programmingLanguage = resources.getStringArray(R.array.programming_language).toList()
        programminDescription = resources.getStringArray(R.array.programming_description).toList()
        adapter = RecyclerViewAdapter(programmingLanguage);
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

    }
}
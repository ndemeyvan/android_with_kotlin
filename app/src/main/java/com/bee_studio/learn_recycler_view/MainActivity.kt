package com.bee_studio.learn_recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bee_studio.learn_recycler_view.BasicViewModel.BasicViewModel

class MainActivity : AppCompatActivity() {

   private lateinit var button : Button;
   private lateinit var countText : TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        countText = findViewById(R.id.textView)
        var viewModel = ViewModelProvider(this).get(BasicViewModel::class.java)
        countText.text=viewModel.num.toString()

        button.setOnClickListener {
            viewModel.addNumber()
            countText.text=viewModel.num.toString()
        }

    }


}
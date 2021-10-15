package com.bee_studio.learn_recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bee_studio.learn_recycler_view.BasicViewModel.BasicViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button;
    private lateinit var countText: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        countText = findViewById(R.id.textView)
        //CountDown timer with liveData
        //1-Cree une instance du ViewModel
        var viewModel = ViewModelProvider(this).get(BasicViewModel::class.java)
        //2- Observer et recuperer la valeur de cette LiveData
        viewModel.seconds().observe(this, {
            countText.text = it.toString()
        })
        //2- Observer et recuperer la valeur de cette LiveData
        viewModel.finished.observe(this, {
            if (it) {
                Toast.makeText(applicationContext, "The counter is finish", Toast.LENGTH_LONG)
                    .show()
            }
        })
        button.setOnClickListener {
            viewModel.startTimer()
        }


    }


}
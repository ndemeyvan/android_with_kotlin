package com.bee_studio.learn_recycler_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.bee_studio.learn_recycler_view.R




class SecondFragment : Fragment() {
    lateinit var secondButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vue = inflater.inflate(R.layout.fragment_second, container, false)
        secondButton = vue.findViewById(R.id.second_button)
        secondButton.setOnClickListener {
            Navigation.findNavController(vue).navigate(R.id.action_secondFragment_to_firstFragment)
        }
        return vue
    }

}
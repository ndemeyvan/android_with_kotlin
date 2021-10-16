package com.bee_studio.learn_recycler_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bee_studio.learn_recycler_view.R




class SecondFragment : Fragment() {
    lateinit var secondButton: Button
    lateinit var titileTxt: TextView
    //Pour recuperer les valeur d'un ecran a un autre
    val args:SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vue = inflater.inflate(R.layout.fragment_second, container, false)
        val number = args.number
        secondButton = vue.findViewById(R.id.second_button)
        titileTxt = vue.findViewById(R.id.title_text)
        titileTxt.text="Hello we are in second fragment with number : $number"
        secondButton.setOnClickListener {
            Navigation.findNavController(vue).navigate(R.id.action_secondFragment_to_firstFragment)
        }
        return vue
    }

}
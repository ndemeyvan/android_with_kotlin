package com.bee_studio.learn_recycler_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.bee_studio.learn_recycler_view.R


class FirstFragment : Fragment() {

    lateinit  var  firstButton : Button;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_first, container, false)
        firstButton = view.findViewById(R.id.first_button)
        firstButton.setOnClickListener {
            //Pour passer les donne d'un ecran a un autre
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(22)
            //Note que si aucune valeur n'avais ete passe sur le nav , il aurait utiliser la valeur par default passe dans le navGraph
            Navigation.findNavController(view).navigate(action)
        }
        return view
    }


}
package com.bee_studio.learn_recycler_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.ShareViewModel.ShareViewModel


class SecondFragment : Fragment() {
    lateinit var secondButton: Button
    lateinit var editText: EditText
    /*Ce qu'il faut retenir ici
    c'est que pour utiliser un viewModel Globalement
    on utilise activityViewModels() */
    private val shareViewModel: ShareViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vue = inflater.inflate(R.layout.fragment_second, container, false)
        secondButton = vue.findViewById(R.id.second_button)
        editText = vue.findViewById(R.id.secondEditText)
        shareViewModel.country.observe(viewLifecycleOwner,{country->
            editText.setText(country)
        })
        secondButton.setOnClickListener {
            shareViewModel.saveCountry(editText.text.toString())
            Navigation.findNavController(vue).navigate(R.id.action_secondFragment_to_firstFragment)
        }
        return vue
    }

}
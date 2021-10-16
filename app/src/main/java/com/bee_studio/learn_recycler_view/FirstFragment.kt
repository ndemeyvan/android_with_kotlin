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
import androidx.navigation.Navigation
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.ShareViewModel.ShareViewModel


class FirstFragment : Fragment() {

    lateinit  var  firstButton : Button;
    lateinit  var  editText : EditText;
    private val shareViewModel:ShareViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_first, container, false)
        firstButton = view.findViewById(R.id.first_button)
        editText = view.findViewById(R.id.firstEditText)
        shareViewModel.country.observe(viewLifecycleOwner,{country->
            editText.setText(country)
        })
        firstButton.setOnClickListener {
            shareViewModel.saveCountry(editText.text.toString())
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(22)
            Navigation.findNavController(view).navigate(action)
        }
        return view
    }


}
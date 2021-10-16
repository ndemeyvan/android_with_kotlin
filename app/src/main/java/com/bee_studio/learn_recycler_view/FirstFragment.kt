package com.bee_studio.learn_recycler_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bee_studio.learn_recycler_view.DataBase.ShoppingDatabase
import com.bee_studio.learn_recycler_view.Repository.ShoppingRepository
import com.bee_studio.learn_recycler_view.ShareViewModel.ShareViewModel
import com.bee_studio.learn_recycler_view.ShareViewModel.ViewModelFactory


class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_first, container, false)
        val database = ShoppingDatabase(requireActivity().application)
        val repository = ShoppingRepository(database)
        val factory = ViewModelFactory(repository)
        //Quand on utilise la delegation ,
        // pour passer une valeur au factory , on l efait comme ici bas
        val shareViewModel: ShareViewModel by activityViewModels() { factory }

        return view
    }


}
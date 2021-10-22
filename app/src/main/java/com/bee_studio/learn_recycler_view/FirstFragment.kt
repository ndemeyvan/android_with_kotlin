package com.bee_studio.learn_recycler_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.grocerylist.data.db.ShoppingDatabase
import com.bee_studio.learn_recycler_view.Adapter.ShoppingAdapter
import com.bee_studio.learn_recycler_view.Repository.ShoppingRepository
import com.bee_studio.learn_recycler_view.ShareViewModel.ShareViewModel
import com.bee_studio.learn_recycler_view.ShareViewModel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FirstFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        //Bind Views
        recyclerView = view.findViewById(R.id.shoppingRecyclerView)
        floatingActionButton = view.findViewById(R.id.floatingActionButton)
        //
        val context = view.context;
        val database = ShoppingDatabase(context)
        val repository = ShoppingRepository(database)
        val factory = ViewModelFactory(repository)
        //Quand on utilise la delegation ,
        // pour passer une valeur au factory , on l efait comme ici bas
        val shareViewModel: ShareViewModel by activityViewModels() { factory }
        //////RECYCLERVIEW Implementation
        var adapter = ShoppingAdapter(listOf(), shareViewModel)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        shareViewModel.getAllShoppingItem().observe(this, Observer {
            adapter.shoppingListItem = it
            adapter.notifyDataSetChanged()
        })

        floatingActionButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_secondFragment)

        }

        return view
    }


}
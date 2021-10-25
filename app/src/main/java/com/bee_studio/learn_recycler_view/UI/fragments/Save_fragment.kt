package com.bee_studio.learn_recycler_view.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.MainActivity
import com.bee_studio.learn_recycler_view.UI.viewModel.NewsViewModel


class Save_fragment : Fragment() {
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_save_fragment, container, false)
        viewModel = (activity as MainActivity).viewModel

        return view
    }


}
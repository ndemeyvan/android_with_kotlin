package com.bee_studio.learn_recycler_view.UI.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.MainActivity
import com.bee_studio.learn_recycler_view.UI.adapters.ArticlesAdapters
import com.bee_studio.learn_recycler_view.UI.data.api.Resource
import com.bee_studio.learn_recycler_view.UI.viewModel.NewsViewModel


class Breaking_news : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter:ArticlesAdapters
    lateinit var rvBreaking:RecyclerView
    lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_breakin_news, container, false)
        rvBreaking = view.findViewById(R.id.rvBreaking)
        progressBar = view.findViewById(R.id.progressBar)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {
                        //Mettre les donnees de la liste a jour
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity,it,Toast.LENGTH_LONG).show();
                    }
                }
            }
        })


        return view
    }

    private fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
        rvBreaking.visibility= View.INVISIBLE
    }

    fun setupRecyclerView(){
        newsAdapter = ArticlesAdapters()
        rvBreaking.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}
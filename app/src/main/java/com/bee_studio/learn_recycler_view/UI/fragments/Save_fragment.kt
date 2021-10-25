package com.bee_studio.learn_recycler_view.UI.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.MainActivity
import com.bee_studio.learn_recycler_view.UI.adapters.ArticlesAdapters
import com.bee_studio.learn_recycler_view.UI.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class Save_fragment : Fragment() {
    lateinit var viewModel: NewsViewModel
    lateinit var svRecyclerview: RecyclerView
    lateinit var newsAdapter: ArticlesAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_save_fragment, container, false)
        viewModel = (activity as MainActivity).viewModel
        svRecyclerview = view.findViewById(R.id.svRecyclerview)
        setupRecyclerView(svRecyclerview.context)
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("article", it)
            }
            findNavController().navigate(
                R.id.action_search_fragment_to_article_fragment,
                bundle
            )
        }

        viewModel.getAllSavedArticle().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.setData(articles)
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.oldArticleList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(svRecyclerview)
        }
        return view
    }



    private fun setupRecyclerView(context: Context) {
        newsAdapter = ArticlesAdapters()
        svRecyclerview.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}
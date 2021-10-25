package com.bee_studio.learn_recycler_view.UI.fragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.MainActivity
import com.bee_studio.learn_recycler_view.UI.adapters.ArticlesAdapters
import com.bee_studio.learn_recycler_view.UI.data.api.Resource
import com.bee_studio.learn_recycler_view.UI.data.api.Utils.Companion.QUERY_PAGE_SIZE
import com.bee_studio.learn_recycler_view.UI.models.Article
import com.bee_studio.learn_recycler_view.UI.viewModel.NewsViewModel


class Breaking_news : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: ArticlesAdapters
    lateinit var rvBreaking: RecyclerView
    lateinit var progressBar: ProgressBar
    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    val TAG = "BreakingNewsFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breakin_news, container, false)
        rvBreaking = view.findViewById(R.id.rvBreaking)
        progressBar = view.findViewById(R.id.progressBar)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView(progressBar.context)

        newsAdapter.setOnItemClickListener {
            Log.i(TAG, "Article is pressed")

            val bundle = Bundle().apply {
                putParcelable("article", it)
            }
            findNavController().navigate(
                R.id.action_breaking_news_to_article_fragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Log.i(TAG, "INFO REQUEST DATA :${it.articles}")
                        //Mettre les donnees de la liste a jour
                        newsAdapter.setData(it?.articles.toList())
                        val totalPages = it.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.page == totalPages
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "ERROR OCCURED", Toast.LENGTH_LONG).show();
                    }
                }
            }
        })

        return view
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.getNews("us")
                isScrolling = false
            } else {
                rvBreaking.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }


    private fun hideProgressBar() {
        isLoading=false
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        isLoading=true
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(context: Context) {
        newsAdapter = ArticlesAdapters()
        rvBreaking.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(scrollListener)
        }
    }





}
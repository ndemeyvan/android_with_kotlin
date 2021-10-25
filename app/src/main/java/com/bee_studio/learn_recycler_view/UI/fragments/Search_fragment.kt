package com.bee_studio.learn_recycler_view.UI.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.*
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.MainActivity
import com.bee_studio.learn_recycler_view.UI.NewsApplication
import com.bee_studio.learn_recycler_view.UI.adapters.ArticlesAdapters
import com.bee_studio.learn_recycler_view.UI.data.api.Resource
import com.bee_studio.learn_recycler_view.UI.data.api.Utils
import com.bee_studio.learn_recycler_view.UI.data.api.Utils.Companion.TIME_DELAY
import com.bee_studio.learn_recycler_view.UI.viewModel.NewsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Search_fragment : Fragment() {


    lateinit var viewModel: NewsViewModel
    lateinit var edSearchNews: EditText
    lateinit var rvSearchNews: RecyclerView
    lateinit var pbSearch: ProgressBar
    lateinit var newsAdapter: ArticlesAdapters
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_fragment, container, false)
        edSearchNews = view.findViewById(R.id.edSearchNews)
        rvSearchNews = view.findViewById(R.id.rvSearchNews)
        pbSearch = view.findViewById(R.id.pbSearch)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView(rvSearchNews.context)
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("article", it)
            }
            findNavController().navigate(
                R.id.action_search_fragment_to_article_fragment,
                bundle
            )
        }
        hideProgressBar()
        var job: Job? = null
        edSearchNews.addTextChangedListener { value ->
            job?.cancel()
            job = MainScope().launch {
                delay(TIME_DELAY)
                value?.let {
                    showProgressBar()
                    if (value.toString().isNotEmpty()) {
                        viewModel.getSearch(value.toString())
                    }else{
                        hideProgressBar()
                        newsAdapter.setData(emptyList())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        print("################# INFO REQUEST SEARCH DATA : ${it.articles}")
                        //Mettre les donnees de la liste a jour
                        newsAdapter.setData(it?.articles)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_LONG).show();
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
            val isTotalMoreThanVisible = totalItemCount >= Utils.QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.getSearch(edSearchNews.text.toString())
                isScrolling = false
            } else {
                rvSearchNews.setPadding(0, 0, 0, 0)
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
        pbSearch.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        pbSearch.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(context:Context) {
        newsAdapter = ArticlesAdapters()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(scrollListener)
        }
    }



}
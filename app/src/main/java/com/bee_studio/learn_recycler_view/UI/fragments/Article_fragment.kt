package com.bee_studio.learn_recycler_view.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bee_studio.learn_recycler_view.R
import com.bee_studio.learn_recycler_view.UI.MainActivity
import com.bee_studio.learn_recycler_view.UI.models.Article
import com.bee_studio.learn_recycler_view.UI.viewModel.NewsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Article_fragment : Fragment() {

    lateinit var viewModel:NewsViewModel
    lateinit var webView:WebView
    lateinit var fab:FloatingActionButton
    private val args by navArgs<Article_fragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_article_fragment, container, false)
        viewModel = (activity as MainActivity).viewModel
        webView = view.findViewById(R.id.webView)
        fab = view.findViewById(R.id.fab)
        val url = args.article.url
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }

        fab.setOnClickListener {
           viewModel.saveArticle(args.article)
            Toast.makeText(context,"ARTICLE SAVE", Toast.LENGTH_LONG ).show()

        }
        return view
    }


}
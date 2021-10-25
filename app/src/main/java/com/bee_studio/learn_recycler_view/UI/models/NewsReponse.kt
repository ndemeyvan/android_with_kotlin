package com.bee_studio.learn_recycler_view.UI.models

data class NewsReponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)
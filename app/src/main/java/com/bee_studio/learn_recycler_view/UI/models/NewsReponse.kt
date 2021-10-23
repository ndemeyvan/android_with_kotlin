package com.bee_studio.learn_recycler_view.UI.models

data class NewsReponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
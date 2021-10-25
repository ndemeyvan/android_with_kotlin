package com.bee_studio.learn_recycler_view.UI.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bee_studio.learn_recycler_view.UI.repo.NewsRepository

class NewsVMFactory(val newsRepository:NewsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}
package com.bee_studio.learn_recycler_view.UI.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bee_studio.learn_recycler_view.UI.repo.NewsRepository

class NewsVMFactory(val newsRepository:NewsRepository,val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(application,newsRepository) as T
    }
}
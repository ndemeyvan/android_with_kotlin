package com.bee_studio.learn_recycler_view.ShareViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bee_studio.learn_recycler_view.Repository.ShoppingRepository

//Un ViewModel Factory sert a passer les donnees a un ViewModel
class ViewModelFactory(private val repository: ShoppingRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShareViewModel(repository) as T
    }
}
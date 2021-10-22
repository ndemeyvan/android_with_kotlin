package com.bee_studio.learn_recycler_view.ShareViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee_studio.learn_recycler_view.Entities.ShoppingItem
import com.bee_studio.learn_recycler_view.Repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShareViewModel(private val repository: ShoppingRepository) : ViewModel() {


    fun insertShoppingItem(item: ShoppingItem) = viewModelScope.launch {
        repository.insertShopping(item)
    }

    fun deleteShoppingItem(item: ShoppingItem) = viewModelScope.launch {
        repository.deleteShopping(item)
    }

    fun getAllShoppingItem() =  repository.getAllShoppingItem()


}
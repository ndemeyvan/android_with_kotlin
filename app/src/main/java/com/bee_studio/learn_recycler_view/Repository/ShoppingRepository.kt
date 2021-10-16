package com.bee_studio.learn_recycler_view.Repository

import androidx.lifecycle.LiveData
import com.bee_studio.learn_recycler_view.DataBase.ShoppingDatabase
import com.bee_studio.learn_recycler_view.Entities.ShoppingItem

class ShoppingRepository(private val db:ShoppingDatabase) {
    suspend fun insertShopping(item:ShoppingItem){
        return db.getShoppingDao().insertShoppingItem(item)
    }
    suspend fun deleteShopping(item:ShoppingItem){
        return db.getShoppingDao().deleteShoppingItem(item)
    }
    suspend fun getAllShoppingItem():LiveData<List<ShoppingItem>>{
        return db.getShoppingDao().getAllShoppingItems()
    }
}
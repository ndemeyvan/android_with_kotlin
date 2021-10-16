package com.bee_studio.learn_recycler_view.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bee_studio.learn_recycler_view.Entities.ShoppingItem

//Un Dao permet de creer toutes les methodes
// qui vont permettres a l'utilisateur d'interagir avec la BD
//@Dao permet a room de voir cette classe comme un Data Access Object

@Dao
interface ShoppingDao {
    //@Insert permet d'inserer un element dans la Bd
    //onConflict definit le comportement sur la BD dans certain cas comme la duplicite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertShoppingItem(item:ShoppingItem)
    @Delete
    suspend fun deleteShoppingItem(item:ShoppingItem)
    @Update
    suspend fun updateShoppingItem(item:ShoppingItem)
    //Query Permet d'ecrire soit meme ses propre requete SQL
    @Query("SELECT * FROM Shopping_items")
    suspend fun getAllShoppingItems(item:ShoppingItem):LiveData<List<ShoppingItem>>

}
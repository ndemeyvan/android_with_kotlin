package com.bee_studio.learn_recycler_view.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Une entite represente une table dans notre Base de donnee
//@Entity dit a room que cette class est une entite
//tableName permet de donner le nom de la table
//ColumnInfo Permet de modifier les truc comme le nom

@Entity(tableName = "Shopping_items")
data class ShoppingItem (

    @ColumnInfo(name = "item_name")
    val name:String,
    @ColumnInfo(name = "item_amount")
    val amount:Int) {
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null
}
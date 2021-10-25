package com.bee_studio.learn_recycler_view.UI.data.api.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bee_studio.learn_recycler_view.UI.models.Article


@Dao
interface DAO {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun saveArticle(article: Article):Long
//
//    //La methode n'est suspendus car LiveData ne fonctionne pas avec suspen
//    @Query("SELECT * From articles ")
//    fun getLocalArticles(): LiveData<List<Article>>
//
//    @Delete
//    suspend fun deleteArticle(article: Article)
}


package com.bee_studio.learn_recycler_view.UI.data.api.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bee_studio.learn_recycler_view.UI.models.Article


@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}


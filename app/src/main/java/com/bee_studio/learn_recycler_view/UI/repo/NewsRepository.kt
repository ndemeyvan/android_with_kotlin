package com.bee_studio.learn_recycler_view.UI.repo

import androidx.lifecycle.LiveData
import com.bee_studio.learn_recycler_view.UI.data.api.NewsApi
import com.bee_studio.learn_recycler_view.UI.data.api.RetrofitInstance
import com.bee_studio.learn_recycler_view.UI.data.api.db.ArticleDataBase
import com.bee_studio.learn_recycler_view.UI.models.Article
import com.bee_studio.learn_recycler_view.UI.models.NewsReponse
import retrofit2.Response

class NewsRepository(val db:ArticleDataBase) {

    suspend fun getBreakingNews( countryCode:String,page:Int) : Response<NewsReponse> {
        return RetrofitInstance.api.getHotlinesArticles(countryCode,page)
    }

    suspend fun searchNews(search:String,page:Int): Response<NewsReponse>{
        return RetrofitInstance.api.searchForNews(search,page)
    }

    suspend fun saveArticle(article: Article): Long {
        return db.getArticleDao().insertArticle(article)
    }
    suspend fun deleteArticle(article: Article) {
        return db.getArticleDao().deleteArticle(article)
    }

     fun getSaveArticle(): LiveData<List<Article>> {
        return db.getArticleDao().getAllArticles()
    }


}
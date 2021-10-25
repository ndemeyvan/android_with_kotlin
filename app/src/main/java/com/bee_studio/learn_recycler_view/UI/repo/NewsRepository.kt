package com.bee_studio.learn_recycler_view.UI.repo

import com.bee_studio.learn_recycler_view.UI.data.api.NewsApi
import com.bee_studio.learn_recycler_view.UI.data.api.RetrofitInstance
import com.bee_studio.learn_recycler_view.UI.data.api.db.ArticleDataBase
import com.bee_studio.learn_recycler_view.UI.models.NewsReponse
import retrofit2.Response

class NewsRepository(val db:ArticleDataBase) {

    suspend fun getBreakingNews( countryCode:String,page:Int) : Response<NewsReponse> {
        return RetrofitInstance.api.getHotlinesArticles(countryCode,page)
    }


}
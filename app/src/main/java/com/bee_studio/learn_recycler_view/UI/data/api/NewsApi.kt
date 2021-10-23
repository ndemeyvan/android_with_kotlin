package com.bee_studio.learn_recycler_view.UI.data.api

import com.bee_studio.learn_recycler_view.UI.data.api.Utils.Companion.API_KEY
import com.bee_studio.learn_recycler_view.UI.models.NewsReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getHotlinesArticles(
        @Query("country") countryCode: String = "us",
        @Query("page") page: Int = 1,
        @Query("apikey") apikey: String = API_KEY
    ):Response<NewsReponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") search: String ,
        @Query("page") page: Int = 1,
        @Query("apikey") apikey: String = API_KEY
    ):Response<NewsReponse>
}
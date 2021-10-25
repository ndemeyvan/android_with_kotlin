package com.bee_studio.learn_recycler_view.UI.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.*
import com.bee_studio.learn_recycler_view.UI.NewsApplication
import com.bee_studio.learn_recycler_view.UI.data.api.Resource
import com.bee_studio.learn_recycler_view.UI.models.Article
import com.bee_studio.learn_recycler_view.UI.models.NewsReponse
import com.bee_studio.learn_recycler_view.UI.repo.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(app: Application,val repo: NewsRepository) :  AndroidViewModel(app) {

    var breakingNews: MutableLiveData<Resource<NewsReponse>> = MutableLiveData()
    var breakingNewsResponse: NewsReponse? =null
    var page = 1

    var searchNews: MutableLiveData<Resource<NewsReponse>> = MutableLiveData()
    var searchNewsResponse: NewsReponse? =null
    var searchPage = 1
    var newSearchQuery:String? = null
    var oldSearchQuery:String? = null


    init {
        print("################# INFO REQUEST : MAKE REQUEST")
        getNews("us")
    }

    fun getNews(countryCode: String) {
        viewModelScope.launch {
            breakingNews.postValue(Resource.Loading())
            val response = repo.getBreakingNews(countryCode, page)
            breakingNews.postValue(handleResponse(response))
        }
    }

    private suspend fun safeBreakingNewsCall(countryCode: String) {
        breakingNews.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = repo.getBreakingNews(countryCode, page)
                breakingNews.postValue(handleResponse(response))
            } else {
                breakingNews.postValue(Resource.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> breakingNews.postValue(Resource.Error("Network Failure"))
                else -> breakingNews.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    private suspend fun safeSearchNewsCall(searchQuery: String) {
        newSearchQuery = searchQuery
        searchNews.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = repo.searchNews(searchQuery, searchPage)
                searchNews.postValue(handleSearchResponse(response))
            } else {
                searchNews.postValue(Resource.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> searchNews.postValue(Resource.Error("Network Failure"))
                else -> searchNews.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    fun getSearch(search:String){
        viewModelScope.launch {
            searchNews.postValue(Resource.Loading())
            val response = repo.searchNews(search, searchPage)
            searchNews.postValue(handleSearchResponse(response))
        }
    }

    fun saveArticle(article: Article){
        viewModelScope.launch {
            repo.saveArticle(article)

        }
    }

    fun deleteArticle(article: Article){
        viewModelScope.launch {
            repo.deleteArticle(article)
        }
    }

    fun getAllSavedArticle(): LiveData<List<Article>> {
        return repo.getSaveArticle()
    }

    private fun handleResponse(response: Response<NewsReponse>): Resource<NewsReponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                page++
                if(breakingNewsResponse==null){
                    breakingNewsResponse = result
                }else{
                    breakingNewsResponse?.articles?.addAll( result.articles)
                }
                return Resource.Success(breakingNewsResponse ?: result)
            }
        }
        return Resource.Error(response.message())

    }

    private fun handleSearchResponse(response: Response<NewsReponse>): Resource<NewsReponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                searchPage++
                if(searchNewsResponse == null || newSearchQuery != oldSearchQuery) {
                    searchPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = result
                }else{
                    searchNewsResponse?.articles?.addAll(result.articles)
                }
                return Resource.Success(searchNewsResponse ?: result)
            }
        }
        return Resource.Error(response.message())

    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ContactsContract.CommonDataKinds.Email.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}
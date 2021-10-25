package com.bee_studio.learn_recycler_view.UI.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee_studio.learn_recycler_view.UI.data.api.Resource
import com.bee_studio.learn_recycler_view.UI.models.NewsReponse
import com.bee_studio.learn_recycler_view.UI.repo.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val repo: NewsRepository) : ViewModel() {

    var breakingNews: MutableLiveData<Resource<NewsReponse>> = MutableLiveData()
    var page = 1

    init {
        getNews("us")
    }

    fun getNews(countryCode: String) {
        viewModelScope.launch {
            breakingNews.postValue(Resource.Loading())
            val response = repo.getBreakingNews(countryCode, page)
            breakingNews.postValue(handleResponse(response))
        }
    }

    private fun handleResponse(response: Response<NewsReponse>): Resource<NewsReponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())

    }


}
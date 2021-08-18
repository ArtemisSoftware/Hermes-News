package com.artemissoftware.hermesnews.ui.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.artemissoftware.hermesnews.models.Article
import com.artemissoftware.hermesnews.models.NewsResponse
import com.artemissoftware.hermesnews.repository.NewsRepository
import com.artemissoftware.hermesnews.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository): ViewModel(){

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response = safeApiCall(apiCall = { newsRepository.getBreakingNews(countryCode, breakingNewsPage)}, successHandler = ::handleBreakingNewsResponse)
        breakingNews.postValue(response)
    }


    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Resource.Success(body)
                }
            }
            return Resource.Error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return Resource.Error(e.message ?: e.toString())
        }
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>, successHandler:(T) ->Resource<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return successHandler(it)
                }
            }
            return Resource.Error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return Resource.Error(e.message ?: e.toString())
        }
    }



    private fun handleBreakingNewsResponse(resultResponse: NewsResponse) : Resource<NewsResponse> {

        breakingNewsPage++
        if(breakingNewsResponse == null) {
            breakingNewsResponse = resultResponse
        } else {
            val oldArticles = breakingNewsResponse?.articles
            val newArticles = resultResponse.articles
            oldArticles?.addAll(newArticles)
        }

        return Resource.Success(breakingNewsResponse ?: resultResponse)
    }





    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                searchNewsPage++
                if(searchNewsResponse == null) {
                    searchNewsResponse = resultResponse
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }

                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }




    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews().asLiveData()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }


}
package com.artemissoftware.hermesnews.repository

import com.artemissoftware.hermesnews.api.NewsApi
import com.artemissoftware.hermesnews.database.ArticleDao
import com.artemissoftware.hermesnews.models.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val articleDao: ArticleDao, private val api: NewsApi): NewsRepository {


    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return api.getBreakingNews(countryCode, pageNumber)
    }

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> {
        return api.searchForNews(searchQuery, pageNumber)
    }


}
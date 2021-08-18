package com.artemissoftware.hermesnews.repository

import com.artemissoftware.hermesnews.api.NewsApi
import com.artemissoftware.hermesnews.database.ArticleDao
import com.artemissoftware.hermesnews.models.Article
import com.artemissoftware.hermesnews.models.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface NewsRepository {


    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse>




    suspend fun upsert(article: Article) : Long

    fun getSavedNews(): Flow<List<Article>>

    suspend fun deleteArticle(article: Article)


}
package com.artemissoftware.hermesnews.repository

import com.artemissoftware.hermesnews.api.NewsApi
import com.artemissoftware.hermesnews.database.ArticleDao
import com.artemissoftware.hermesnews.models.Article
import com.artemissoftware.hermesnews.models.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val articleDao: ArticleDao, private val api: NewsApi): NewsRepository {


    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return api.getBreakingNews(countryCode, pageNumber)
    }

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> {
        return api.searchForNews(searchQuery, pageNumber)
    }


    override suspend fun upsert(article: Article): Long {
        return articleDao.upsert(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }


}
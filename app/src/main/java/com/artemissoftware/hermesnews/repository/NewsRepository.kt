package com.artemissoftware.hermesnews.repository

import com.artemissoftware.hermesnews.database.ArticleDao
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val articleDao: ArticleDao
) {


}
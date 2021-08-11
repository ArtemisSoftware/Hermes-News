package com.artemissoftware.hermesnews.di

import com.artemissoftware.hermesnews.api.NewsApi
import com.artemissoftware.hermesnews.database.ArticleDao
import com.artemissoftware.hermesnews.repository.NewsRepository
import com.artemissoftware.hermesnews.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NewsModule {

    @Singleton
    @Provides
    fun provideMainRepository(articleDao: ArticleDao, api: NewsApi): NewsRepository = NewsRepositoryImpl(articleDao, api)

}
package com.artemissoftware.hermesnews.di

import android.app.Application
import com.artemissoftware.hermesnews.api.NewsApi
import com.artemissoftware.hermesnews.util.ApiConstants.Companion.BASE_URL
import com.artemissoftware.hermesnews.util.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(application: Application): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)


        val networkInterceptor = NetworkInterceptor(application)


        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(networkInterceptor)
            .build()

    }


    @Singleton
    @Provides
    fun provideNewsApi(client: OkHttpClient): NewsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(NewsApi::class.java)

}
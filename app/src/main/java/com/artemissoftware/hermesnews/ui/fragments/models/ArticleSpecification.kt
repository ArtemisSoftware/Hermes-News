package com.artemissoftware.hermesnews.ui.fragments.models

import android.os.Parcelable
import com.artemissoftware.hermesnews.models.Article
import com.artemissoftware.hermesnews.models.Source
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleSpecification (
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable{

    fun getArticle(): Article{

        return Article(author = author, content = content, description = description, publishedAt = publishedAt, source = Source(source, source), title = title, url = url, urlToImage = urlToImage)

    }

}
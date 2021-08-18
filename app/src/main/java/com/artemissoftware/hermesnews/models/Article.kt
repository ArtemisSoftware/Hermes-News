package com.artemissoftware.hermesnews.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.artemissoftware.hermesnews.ui.fragments.models.ArticleSpecification

@Entity(
    tableName = "articles"
)
data class Article(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
){

    fun getArticleSpecification(): ArticleSpecification{
        return ArticleSpecification(author, content, description, publishedAt, source.name, title, url, urlToImage)
    }


}
package com.artemissoftware.hermesnews.ui.fragments

import androidx.hilt.lifecycle.ViewModelInject
import com.artemissoftware.hermesnews.repository.NewsRepository

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) {
}
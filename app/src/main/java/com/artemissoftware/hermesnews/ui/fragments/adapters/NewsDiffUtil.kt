package com.artemissoftware.hermesnews.ui.fragments.adapters

import androidx.recyclerview.widget.DiffUtil
import com.artemissoftware.hermesnews.models.Article

object NewsDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
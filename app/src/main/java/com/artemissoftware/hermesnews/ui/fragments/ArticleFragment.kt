package com.artemissoftware.hermesnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.artemissoftware.hermesnews.R
import com.artemissoftware.hermesnews.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(args.article.url)
        }


        fab.setOnClickListener {
            viewModel.saveArticle(args.article.getArticle())
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }

    }
}
package com.artemissoftware.hermesnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemissoftware.hermesnews.R
import com.artemissoftware.hermesnews.ui.MainActivity
import com.artemissoftware.hermesnews.ui.fragments.adapters.NewsAdapter
import kotlinx.android.synthetic.main.fragment_search_news.*

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
        setupClickListeners()
    }


    private fun setupClickListeners(){

        newsAdapter.setOnItemClickListener {

            val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(it.getArticleSpecification())
            findNavController().navigate(action)

        }
    }



    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}
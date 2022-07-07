package com.example.android.movieguidehilt.presentation.top_rated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingSource
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movieguidehilt.R
import com.example.android.movieguidehilt.adapters.TopRatedPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedActivity : AppCompatActivity() {

    private val viewModel: TopRatedViewModel by viewModels()

    private val adapter = TopRatedPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_rated)

        val topRatedView: RecyclerView = findViewById(R.id.topRatedRV)
        topRatedView.layoutManager =
            GridLayoutManager(this, 2)

        topRatedView.adapter = adapter


        lifecycleScope.launch {

            viewModel.getTopRatedTVShows().collect {
                    item -> adapter.submitData(item)

                topRatedView.adapter = adapter
            }

        }
    }
}
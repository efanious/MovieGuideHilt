package com.example.android.movieguidehilt.presentation.trending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movieguidehilt.R
import com.example.android.movieguidehilt.adapters.MoviesAdapter
import com.example.android.movieguidehilt.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class TrendingActivity : AppCompatActivity() {

    private val viewModel: TrendingViewModel by viewModels()
    private lateinit var progressLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending)

        progressLoading = findViewById(R.id.loading_spinner)

        val trendingRView: RecyclerView = findViewById(R.id.trending_recycler_view)
        trendingRView.layoutManager =
            GridLayoutManager(this, 2)
        val trendingMoviesAdapter = MoviesAdapter()


        viewModel.getTrendingMovies()

        viewModel.response.observe(this) {
            result ->
            when (result) {
                is Resource.Success -> {
                    progressLoading.visibility = View.GONE
                    //Toast.makeText(this, "${result.data?.results?.size}", Toast.LENGTH_SHORT).show()

                    trendingMoviesAdapter.data = result.data?.results!!
                    trendingRView.adapter = trendingMoviesAdapter

                }
                is Resource.Error -> {
                    progressLoading.visibility = View.GONE
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    progressLoading.visibility = View.VISIBLE

                }
            }
        }

    }
}
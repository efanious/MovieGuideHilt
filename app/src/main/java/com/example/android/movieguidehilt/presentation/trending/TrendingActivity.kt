package com.example.android.movieguidehilt.presentation.trending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.android.movieguidehilt.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TrendingActivity : AppCompatActivity() {

    private val viewModel: TrendingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending)

        viewModel.getTrendingMovies()

        viewModel.response.observe(this) {
            Toast.makeText(this, "${ it.results?.size}", Toast.LENGTH_SHORT).show()
        }

        viewModel.errorResponse.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}
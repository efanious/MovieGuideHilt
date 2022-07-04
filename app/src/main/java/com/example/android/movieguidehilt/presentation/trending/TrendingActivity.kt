package com.example.android.movieguidehilt.presentation.trending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.android.movieguidehilt.R
import com.example.android.movieguidehilt.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TrendingActivity : AppCompatActivity() {

    private val viewModel: TrendingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending)

        viewModel.getTrendingMovies()

        viewModel.response.observe(this) {
            result ->
            when (result) {
                is Resource.Success -> Toast.makeText(this, "${result.data?.results?.size}", Toast.LENGTH_SHORT).show()
                is Resource.Error -> Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                //is Resource.Loading -> handleError(result.error)
            }
            //Toast.makeText(this, "${ it.results?.size}", Toast.LENGTH_SHORT).show()
        }
//
//        viewModel.errorResponse.observe(this) {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//        }
    }
}
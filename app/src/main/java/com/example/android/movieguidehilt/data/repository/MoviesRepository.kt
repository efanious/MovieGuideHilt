package com.example.android.movieguidehilt.data.repository

import com.example.android.movieguidehilt.data.remote.dto.TrendingMoviesResponse


interface MoviesRepository {

    suspend fun getTrendingMovies(): TrendingMoviesResponse
}
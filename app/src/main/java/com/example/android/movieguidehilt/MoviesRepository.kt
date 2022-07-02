package com.example.android.movieguidehilt


interface MoviesRepository {

    suspend fun getTrendingMovies(): TrendingMoviesResponse
}
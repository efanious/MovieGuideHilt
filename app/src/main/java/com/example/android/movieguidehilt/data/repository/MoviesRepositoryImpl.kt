package com.example.android.movieguidehilt.data.repository

import com.example.android.movieguidehilt.data.remote.dto.TrendingMoviesResponse
import com.example.android.movieguidehilt.data.remote.MovieGuideApi
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(private val movieGuideApi: MovieGuideApi) :
    MoviesRepository {

    override suspend fun getTrendingMovies(): TrendingMoviesResponse {
        return movieGuideApi.getTrendingMovies()
    }
}
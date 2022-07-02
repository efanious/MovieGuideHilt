package com.example.android.movieguidehilt

import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(private val movieGuideApi: MovieGuideApi) :
    MoviesRepository {

    override suspend fun getTrendingMovies(): TrendingMoviesResponse {
        return movieGuideApi.getTrendingMovies()
    }
}
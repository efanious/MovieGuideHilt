package com.example.android.movieguidehilt.data.repository

import com.example.android.movieguidehilt.data.remote.dto.TrendingMoviesResponse
import com.example.android.movieguidehilt.util.Resource
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {

    //suspend fun getTrendingMovies(): TrendingMoviesResponse
    suspend fun getTrendingMovies(): Flow<Resource<TrendingMoviesResponse>>
}
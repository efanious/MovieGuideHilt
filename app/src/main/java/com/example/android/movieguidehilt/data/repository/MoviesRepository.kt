package com.example.android.movieguidehilt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.android.movieguidehilt.domain.model.TrendingMoviesResponse
import com.example.android.movieguidehilt.domain.model.Result
import com.example.android.movieguidehilt.util.Resource
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {

    suspend fun getTrendingMovies(): Flow<Resource<TrendingMoviesResponse>>

    @OptIn(ExperimentalPagingApi::class)
    fun getTopRatedTVShowsStream(): Flow<PagingData<Result>>
}
package com.example.android.movieguidehilt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.movieguidehilt.adapters.TopRatedPagingSource
import com.example.android.movieguidehilt.data.remote.dto.TrendingMoviesResponse
import com.example.android.movieguidehilt.data.remote.dto.Result
import com.example.android.movieguidehilt.data.remote.MovieGuideApi
import com.example.android.movieguidehilt.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(private val movieGuideApi: MovieGuideApi) :
    MoviesRepository {

    //MoviesRepository
    override suspend fun getTrendingMovies(): Flow<Resource<TrendingMoviesResponse>> = flow {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(movieGuideApi.getTrendingMovies()))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops something went wrong! " + e.message
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Oops something went wrong! " + e.message
                )
            )
        }

    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedTVShowsStream(): Flow<PagingData<Result>> {
        return  Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopRatedPagingSource(movieGuideApi) }
        ).flow
    }

}

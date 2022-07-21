package com.example.android.movieguidehilt.presentation.top_rated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android.movieguidehilt.data.repository.MoviesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.android.movieguidehilt.domain.model.Result

@HiltViewModel
class TopRatedViewModel @Inject constructor(private val moviesRepositoryImpl: MoviesRepositoryImpl) :
    ViewModel() {

    fun getTopRatedTVShows(): Flow<PagingData<Result>> {
        return moviesRepositoryImpl.getTopRatedTVShowsStream().cachedIn(viewModelScope)

    }
}
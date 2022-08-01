package com.example.android.movieguidehilt.presentation.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movieguidehilt.data.repository.MoviesRepositoryImpl
import com.example.android.movieguidehilt.domain.model.TrendingMoviesResponse
import com.example.android.movieguidehilt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrendingViewModel @Inject constructor(private val moviesRepositoryImpl: MoviesRepositoryImpl) :
    ViewModel() {


    private val _response = MutableLiveData<Resource<TrendingMoviesResponse>>()

    val response: LiveData<Resource<TrendingMoviesResponse>>
        get() = _response


    fun getTrendingMovies() {
        viewModelScope.launch {

            moviesRepositoryImpl.getTrendingMovies().collect { result ->

                when (result) {
                    is Resource.Success -> {
                        _response.postValue(Resource.Success(result.data))
                    }
                    is Resource.Error -> {
                        _response.postValue(result.message?.let { Resource.Error(it) })
                    }
                    is Resource.Loading -> {
                        _response.postValue(Resource.Loading(null))
                        //delay(2000)
                    }
                }

            }
        }
    }

}


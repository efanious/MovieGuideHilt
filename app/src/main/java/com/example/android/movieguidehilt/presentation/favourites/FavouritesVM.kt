package com.example.android.movieguidehilt.presentation.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movieguidehilt.data.local.MovieDao
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.android.movieguidehilt.data.local.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesVM @Inject constructor(private val movieDao: MovieDao): ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()

    val movie: LiveData<List<Movie>>
        get() = _movies

    fun checkSizeOfMovies() {
        viewModelScope.launch {
            _movies.value = movieDao.getAllFavourites()
        }
    }

    fun deleteMovieFromDb(item: Movie?) {
        viewModelScope.launch {
            movieDao.deleteMovie(
                Movie(
                    adult = item?.adult,
                    backdropPath = item?.backdropPath,
                    id = item?.id,
                    originalLanguage = item?.originalLanguage,
                    originalTitle = item?.originalTitle,
                    overview = item?.overview,
                    posterPath = item?.posterPath,
                    releaseDate = item?.releaseDate,
                    title = item?.title,
                    video = item?.video,
                    originalName = item?.originalName,
                )
            )
        }

        checkSizeOfMovies()
    }
}
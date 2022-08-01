package com.example.android.movieguidehilt.presentation.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movieguidehilt.data.local.Movie
import com.example.android.movieguidehilt.data.local.MovieDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.android.movieguidehilt.domain.model.Result


//@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val database: MovieDao) : ViewModel() {


    fun checkSizeOfMovies(): Int {

        var result = 0
        viewModelScope.launch {
            result = database.getAllFavourites().size
        }

        return result
    }

    private var _movie: Result? = null

    private var _movInDb = MutableLiveData<Boolean>()
    val movInDb: LiveData<Boolean>
        get() = _movInDb

    fun setMovie(movie: Result) {
        _movie = movie

        viewModelScope.launch {
            _movInDb.value = _movie?.id?.let { database.exists(it) }
        }
    }

    fun addOrRemoveAsFav() {
        viewModelScope.launch {

            if (movInDb.value == true) {
                database.deleteMovie(
                    Movie(
                        adult = _movie?.adult,
                        backdropPath = _movie?.backdropPath,
                        id = _movie?.id,
                        originalLanguage = _movie?.originalLanguage,
                        originalTitle = _movie?.originalTitle,
                        overview = _movie?.overview,
                        posterPath = _movie?.posterPath,
                        releaseDate = _movie?.releaseDate,
                        title = _movie?.title,
                        video = _movie?.video,
                        originalName = _movie?.originalName
                    )
                )
            } else {
                database.insert(
                    Movie(
                        adult = _movie?.adult,
                        backdropPath = _movie?.backdropPath,
                        id = _movie?.id,
                        originalLanguage = _movie?.originalLanguage,
                        originalTitle = _movie?.originalTitle,
                        overview = _movie?.overview,
                        posterPath = _movie?.posterPath,
                        releaseDate = _movie?.releaseDate,
                        title = _movie?.title,
                        video = _movie?.video,
                        originalName = _movie?.originalName,
                    )
                )
            }

            _movInDb.value = _movie?.id?.let { database.exists(it) }
        }

    }
}
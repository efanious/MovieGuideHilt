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


@HiltViewModel
class MovDetVM @Inject constructor(private val movieDao: MovieDao) :
    ViewModel() {

    //MovieDetailViewModel
   // var result = 0

    //lateinit var movieDao: MovieDao
    //    @Inject constructor(private val movieDao: MovieDao)
    // @Inject constructor(private val dbRepositoryImpl: DbRepositoryImpl)
//    @Inject constructor(private val database: MovieDao)


//    fun checkSizeOfMovies(): Int {
//
//        // test db
//        viewModelScope.launch {
//            result = movieDao.getAllFavourites().size
//        }
//
//        return result
//    }

    private var _movie: Result? = null

    private var _movInDb = MutableLiveData<Boolean>()
    val movInDb: LiveData<Boolean>
        get() = _movInDb

    fun setMovie(movie: Result) {
        _movie = movie

        viewModelScope.launch {
            _movInDb.value = _movie?.id?.let { movieDao.exists(it) }
        }
    }

    fun addOrRemoveAsFav() {
        viewModelScope.launch {

            if (movInDb.value == true) {
                movieDao.deleteMovie(
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
                movieDao.insert(
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

            _movInDb.value = _movie?.id?.let { movieDao.exists(it) }
        }

    }
}
package com.example.android.movieguidehilt.presentation.movie_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.android.movieguidehilt.domain.model.Result
import com.example.android.movieguidehilt.R
import com.example.android.movieguidehilt.data.local.MovieDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private val movDetVM: MovDetVM by viewModels()

    @Inject lateinit var movieDao: MovieDao

    private lateinit var movie: Result


    private lateinit var titleTextView: TextView
    private lateinit var backdropImageView: ImageView
    private lateinit var posterImageView: ImageView
    private lateinit var overviewTextView: TextView
    private lateinit var favButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movie = intent.getParcelableExtra("Movie")!!

        titleTextView = findViewById(R.id.title_textview)
        backdropImageView = findViewById(R.id.backdropImageView)
        posterImageView = findViewById(R.id.posterImageView)
        overviewTextView = findViewById(R.id.overview_textview)

        favButton = findViewById(R.id.favourite_button)

        //movieDetailViewModel.movieDao = movieDao

        movDetVM.setMovie(movie)
        displayInfo(movie)

        movDetVM.movInDb.observe(this) {
            if (it) {
                //favButton.text = getString(R.string.remove_as_fav)
                favButton.text = "Remove as Favourite"
            } else {
                favButton.text = "Add as Favourites"
            }
        }

        favButton.setOnClickListener {
            movDetVM.addOrRemoveAsFav()

//            Toast.makeText(this, "No of favs: ${movDetVM.checkSizeOfMovies()}", Toast.LENGTH_SHORT)
//                .show()

        }
    }

    private fun displayInfo(movie: Result) {

        titleTextView.text = movie.originalTitle ?: movie.originalName

        val fullBackDropPath = "https://image.tmdb.org/t/p/w500" + movie.backdropPath

        Glide.with(this)
            .load(fullBackDropPath)
            .skipMemoryCache(true)
            .into(backdropImageView)

        val posterPath = "https://image.tmdb.org/t/p/w500" + movie.posterPath

        Glide.with(this)
            .load(posterPath)
            .skipMemoryCache(true)
            .into(posterImageView)

        overviewTextView.text = movie.overview

    }

}
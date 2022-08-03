package com.example.android.movieguidehilt.presentation.favourites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movieguidehilt.R
import com.example.android.movieguidehilt.adapters.FavouritesAdapter
import com.example.android.movieguidehilt.data.local.Movie
import com.example.android.movieguidehilt.presentation.movie_detail.MovDetVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesActivity : AppCompatActivity(), FavouritesAdapter.OnItemClickListener {

    private val favouritesVM: FavouritesVM by viewModels()

    private lateinit var emptyLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        val favouritesRView: RecyclerView = findViewById(R.id.favourites_rv)
        favouritesRView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val favAdapter = FavouritesAdapter(this)

        emptyLayout = findViewById(R.id.emptyListLayout)

        favouritesVM.checkSizeOfMovies()

        favouritesVM.movie.observe(this) {

            if (it.isEmpty()) {
                emptyLayout.visibility = View.VISIBLE
                favouritesRView.visibility = View.GONE
            } else {
                emptyLayout.visibility = View.GONE
                favouritesRView.visibility = View.VISIBLE
                favAdapter.data = it
                favouritesRView.adapter = favAdapter

            }
        }
    }

    override fun onItemClick(item: Movie?) {
        favouritesVM.deleteMovieFromDb(item)
    }
}
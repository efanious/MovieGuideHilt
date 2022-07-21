package com.example.android.movieguidehilt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.movieguidehilt.R
import com.example.android.movieguidehilt.data.remote.dto.ResultDto
import com.example.android.movieguidehilt.domain.model.Result


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    var data = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_view, parent, false)
        return MoviesViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size


    class MoviesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val titleText: TextView = itemView.findViewById(R.id.titleTextView)
        private val posterPath: ImageView = itemView.findViewById(R.id.image_icon)

        private var movie: Result? = null

        init {
            itemView.setOnClickListener {
                movie?.let {
                    onClick(it)
                }
            }
        }

        private fun onClick(movieClicked: Result) {

//            val intent = Intent(itemView.context, MovieDetailActivity::class.java)
//            intent.putExtra("Movie", movieClicked)
//            itemView.context.startActivity(intent)

        }

        fun bind(mMovie: Result) {
            movie = mMovie

            titleText.text = movie!!.originalTitle ?: movie!!.originalName
            val fullPosterPath = "https://image.tmdb.org/t/p/w500" + movie!!.posterPath

            Glide.with(itemView.context)
                .load(fullPosterPath)
                .skipMemoryCache(true)
                .into(posterPath)

        }
    }


}


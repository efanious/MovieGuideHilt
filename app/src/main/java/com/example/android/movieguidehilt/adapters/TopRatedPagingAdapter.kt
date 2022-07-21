package com.example.android.movieguidehilt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.movieguidehilt.R
import com.example.android.movieguidehilt.domain.model.Result


class TopRatedPagingAdapter : PagingDataAdapter<Result, TopRatedPagingAdapter.TopRatedPagingViewHolder>(DiffUtilCallBack()) {

    //var data = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedPagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_view, parent, false)
        return TopRatedPagingViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: TopRatedPagingViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }



    class TopRatedPagingViewHolder(itemView: View) :
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

            Toast.makeText(itemView.context, "${movieClicked.originalName} was clicked", Toast.LENGTH_SHORT).show()
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

class DiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}

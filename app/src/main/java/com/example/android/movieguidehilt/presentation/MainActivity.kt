package com.example.android.movieguidehilt.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.android.movieguidehilt.R
import com.example.android.movieguidehilt.presentation.favourites.FavouritesActivity
import com.example.android.movieguidehilt.presentation.top_rated.TopRatedActivity
import com.example.android.movieguidehilt.presentation.trending.TrendingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trendingBtn = findViewById<Button>(R.id.trending_button)
        trendingBtn.setOnClickListener {
            val intent = Intent(
                this,
                TrendingActivity::class.java
            )
            startActivity(intent)
        }

        val topRatedBtn = findViewById<Button>(R.id.top_rated_button)
        topRatedBtn.setOnClickListener {
            val intent = Intent(
                this,
                TopRatedActivity::class.java
            )
            startActivity(intent)
        }

        val favouritesBtn = findViewById<Button>(R.id.favourites_button)
        favouritesBtn.setOnClickListener {
            val intent = Intent(
                this,
                FavouritesActivity::class.java
            )
            startActivity(intent)
        }


    }
}
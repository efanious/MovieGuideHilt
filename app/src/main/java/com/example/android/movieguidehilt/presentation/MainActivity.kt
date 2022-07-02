package com.example.android.movieguidehilt.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.android.movieguidehilt.R
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


    }
}
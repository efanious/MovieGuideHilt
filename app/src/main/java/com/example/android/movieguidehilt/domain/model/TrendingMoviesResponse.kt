package com.example.android.movieguidehilt.domain.model

data class TrendingMoviesResponse(
    val page: Int?,
    val results: List<Result>?,
    val totalPages: Int?,
    val totalResults: Int?
)
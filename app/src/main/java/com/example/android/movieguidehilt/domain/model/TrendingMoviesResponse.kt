package com.example.android.movieguidehilt.domain.model

import com.example.android.movieguidehilt.data.remote.dto.ResultDto

data class TrendingMoviesResponse(
    val page: Int?,
    val results: List<Result>?,
    val totalPages: Int?,
    val totalResults: Int?
)
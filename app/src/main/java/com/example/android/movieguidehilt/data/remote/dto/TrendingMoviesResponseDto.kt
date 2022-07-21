package com.example.android.movieguidehilt.data.remote.dto

import com.example.android.movieguidehilt.domain.model.TrendingMoviesResponse

data class TrendingMoviesResponseDto(

    val page: Int?,
    val results: List<ResultDto>?,
    val total_pages: Int?,
    val total_results: Int?
) {
    fun toTrendingMoviesResponse(): TrendingMoviesResponse {
        return TrendingMoviesResponse(
            page = page,
            results = results?.map { it.toResult() },
            totalPages = total_pages,
            totalResults = total_results
        )
    }
}

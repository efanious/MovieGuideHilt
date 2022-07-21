package com.example.android.movieguidehilt.data.remote.dto

import com.example.android.movieguidehilt.domain.model.Result


data class ResultDto(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?,
    val popularity: Double?,
    val original_name: String?,

    ) {
    fun toResult(): Result {
        return Result(
            adult = adult,
            backdropPath = backdrop_path,
            id = id,
            originalLanguage = original_language,
            originalTitle = original_title,
            overview = overview,
            posterPath = poster_path,
            releaseDate = release_date,
            title = title,
            video = video,
            originalName = original_name
        )
    }
}

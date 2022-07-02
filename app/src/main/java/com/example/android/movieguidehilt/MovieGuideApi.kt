package com.example.android.movieguidehilt

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//private const val BASE_URL = "https://api.themoviedb.org"

//private const val API_KEY = "a42804cc4643c432ddbb2090891cb875"

interface MovieGuideApi {

    @GET("/3/trending/{media_type}/{time_window}")
    suspend fun getTrendingMovies(
        @Path("media_type") mediaType: String = "all",
        @Path("time_window") timeWindow: String = "week",
        @Query("api_key") apiKey: String = "a42804cc4643c432ddbb2090891cb875",
    ): TrendingMoviesResponse

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTV(
        @Query("api_key") apiKey: String = "a42804cc4643c432ddbb2090891cb875",
        @Query("page") page: Int
    ): TrendingMoviesResponse

}
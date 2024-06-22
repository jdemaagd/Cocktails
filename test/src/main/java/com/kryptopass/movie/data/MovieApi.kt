package com.kryptopass.movie.data

import com.kryptopass.movie.BuildConfig
import com.kryptopass.movie.model.Movie
import com.kryptopass.movie.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") pageNumber: Int = 1,
    ): MovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") movieId: Int?,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Movie
}

package com.kryptopass.tmp.data

import com.kryptopass.tmp.model.MovieResponse

class MovieRepository(
    private val movieApi: MovieApi,
    private val apiKey: String
) {
    suspend fun getPopularMovies(): MovieResponse {
        return movieApi.getPopularMovies(apiKey)
    }
}


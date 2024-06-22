package com.kryptopass.test.data

import com.kryptopass.test.model.MovieResponse

class MovieRepository(
    private val movieApi: MovieApi,
    private val apiKey: String
) {
    suspend fun getPopularMovies(): MovieResponse {
        return movieApi.getPopularMovies(apiKey)
    }
}


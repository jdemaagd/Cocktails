package com.kryptopass.movie.data

import com.kryptopass.movie.domain.repository.MovieRepository
import com.kryptopass.movie.model.Movie
import com.kryptopass.movie.model.MovieResponse

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val apiKey: String
): MovieRepository {

    override suspend fun getPopularMovies(): MovieResponse {
        return movieApi.getPopularMovies(apiKey)
    }

    override suspend fun getMovieById(id: Int): Movie {
        return movieApi.getMovieById(id, apiKey)
    }
}
